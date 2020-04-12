package com.p.s.core.service.impl;


import com.hbase.dao.HBaseOperations;
import com.hbase.dao.mapper.RowMapper;
import com.p.s.core.model.RequestQuery;
import com.p.s.core.service.ApplicationTraceIndexService;
import com.p.s.pinpoint.hbase.HBaseTables;
import com.p.s.pinpoint.mapper.TransactionMetadataMapper;
import com.p.s.pinpoint.model.Range;
import com.p.s.pinpoint.model.TransactionId;
import com.p.s.pinpoint.model.TransactionMetadata;
import com.p.s.pinpoint.utils.SpanUtils;
import com.sematext.hbase.wd.AbstractRowKeyDistributor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ApplicationTraceIndexServiceImpl implements ApplicationTraceIndexService {


    @Autowired
    private HBaseOperations hbaseOperations;

    @Autowired
    @Qualifier("transactionIdMapper")
    private RowMapper<List<TransactionId>> traceIndexMapper;

    @Autowired
    @Qualifier("applicationTraceIndexDistributor")
    private AbstractRowKeyDistributor traceIdRowKeyDistributor;


    @Value("${query.limit:1000}")
    private int queryLimit;

    @Value("${hbase.num.parallel.threads:16}")
    private int numParallelThreads;


    /**
     * 查询请求的Transaction id
     * @param appName 应用名称
     * @param range 查询开始范围  @return 索引列表
     * @param query 查询参数
     */
    @Override
    public List<TransactionId> getTraceIndex(String appName, Range range, RequestQuery query) {

        Assert.notNull(appName, "ppName 不允许为空！");

        Assert.notNull(range, "Range 不允许为空");

        Assert.notNull(query, "RequestQuery 查询条件不允许为空");

        log.info("query trace index start !");

        List<TransactionId> tidList = new ArrayList<>();

        Scan scan = createTraceIndexScan(appName, range);

        List<Filter> filterList = buildFilterList(query);

        if(!filterList.isEmpty()) {
            scan.setFilter(new FilterList(FilterList.Operator.MUST_PASS_ALL, filterList));
        }

        hbaseOperations.findParallel(HBaseTables.APPLICATION_TRACE_INDEX,
                scan, traceIdRowKeyDistributor, queryLimit, traceIndexMapper, numParallelThreads)
                .forEach(tidList::addAll);

        log.info("query trace index success ! tid size : {}", tidList.size());

        return tidList;
    }


    @Override
    public List<TransactionMetadata> getTraceIndexMetadata(String appName, Range range, RequestQuery query) {

        Assert.notNull(appName, "ppName 不允许为空！");

        Assert.notNull(range, "Range 不允许为空");

        Assert.notNull(query, "RequestQuery 查询条件不允许为空");

        log.info("query trace index start !");

        List<TransactionMetadata> tidMetadataList = new ArrayList<>();

        Scan scan = createTraceIndexScan(appName, range);

        List<Filter> filterList = buildFilterList(query);

        if(!filterList.isEmpty()) {
            scan.setFilter(new FilterList(FilterList.Operator.MUST_PASS_ALL, filterList));
        }

        TransactionMetadataMapper mapper = new TransactionMetadataMapper(query.getAgentIdSet(), query.getRpcName(), query.isHasErr(), query.getThreshold());

        hbaseOperations.findParallel(HBaseTables.APPLICATION_TRACE_INDEX,
                scan, traceIdRowKeyDistributor, queryLimit, mapper, numParallelThreads).stream().filter(Objects::nonNull)
                .forEach(tidMetadataList::addAll);

        log.info("query trace index success ! tid size : {}", tidMetadataList.size());

        return tidMetadataList;
    }

    @Override
    public List<TransactionMetadata> getTraceIndexMetadata4AllRange(String appName, Range range, RequestQuery query) {

        List<TransactionMetadata> result = new ArrayList<>();

        List<TransactionMetadata> metadataList;

        TransactionMetadata metadata = null;

        Range r;

        do {

            r = metadata == null ? range : new Range(range.getFrom(), metadata.getCollectorAcceptorTime() - 1);

            metadataList = getTraceIndexMetadata(appName, r, query);

            if(!metadataList.isEmpty()) {
                metadata = metadataList.get(metadataList.size() - 1);
                result.addAll(metadataList);
            }

        } while (!metadataList.isEmpty() && metadata != null && metadata.getCollectorAcceptorTime() >= r.getFrom());

        return result;
    }



    /**
     * 创建TraceIndex Scan
     * @param applicationName 应用名称
     * @param range 查询范围
     * @return Scan
     */
    private Scan createTraceIndexScan(String applicationName, Range range) {

        Scan scan = new Scan();
        scan.setCaching(512);

        byte[] bApplicationName = Bytes.toBytes(applicationName);
        byte[] traceIndexStartKey = SpanUtils.getTraceIndexRowKey(bApplicationName, range.getFrom());
        byte[] traceIndexEndKey = SpanUtils.getTraceIndexRowKey(bApplicationName, range.getTo());


        scan.setStartRow(traceIndexEndKey);
        scan.setStopRow(traceIndexStartKey);

        scan.addFamily(HBaseTables.APPLICATION_TRACE_INDEX_CF_TRACE);
        scan.setId("ApplicationTraceIndexScan");

        return scan;
    }


    /**
     * 根据传递的查询条件构建Filter列表
     * @param query 查询条件
     * @return 构建好的Filter列表
     */
    private List<Filter> buildFilterList(RequestQuery query) {

        List<Filter> filterList = new ArrayList<>();

        if(query.getThreshold() > 0) {
            filterList.add(createSingleColumnValueFilter("EL", CompareFilter.CompareOp.GREATER, new BinaryComparator(Bytes.toBytes(query.getThreshold()))));
        }

        if(StringUtils.hasText(query.getRpcName())) {
            filterList.add(createSingleColumnValueFilter("RPC", CompareFilter.CompareOp.EQUAL, new SubstringComparator(query.getRpcName())));
        }

        if(query.isHasErr()) {
            filterList.add(createSingleColumnValueFilter("ERR", CompareFilter.CompareOp.NOT_EQUAL, new BinaryComparator(Bytes.toBytes(0))));
        }

        if(!CollectionUtils.isEmpty(query.getAgentIdSet())) {

            FilterList agentIdFilterList = new FilterList(FilterList.Operator.MUST_PASS_ONE,
                    query.getAgentIdSet()
                            .stream().map(agentId -> createSingleColumnValueFilter("AID", CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(agentId))))
                            .collect(Collectors.toList()));

            filterList.add(agentIdFilterList);
        }

        return filterList;
    }


    private SingleColumnValueFilter createSingleColumnValueFilter(String qualifier, CompareFilter.CompareOp op, ByteArrayComparable comparator) {
        SingleColumnValueFilter filter = new SingleColumnValueFilter(HBaseTables.APPLICATION_TRACE_INDEX_CF_TRACE, Bytes.toBytes(qualifier), op, comparator);
        filter.setFilterIfMissing(true);
        return filter;
    }

}
