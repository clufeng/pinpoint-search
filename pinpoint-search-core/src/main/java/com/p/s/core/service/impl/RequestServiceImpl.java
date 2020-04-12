package com.p.s.core.service.impl;

import com.p.s.core.model.Request;
import com.p.s.core.model.RequestQuery;
import com.p.s.core.service.ApplicationTraceIndexService;
import com.p.s.core.service.ApplicationTraceService;
import com.p.s.core.service.RequestService;
import com.p.s.core.utils.CommonUtils;
import com.p.s.pinpoint.model.Application;
import com.p.s.pinpoint.model.Range;
import com.p.s.pinpoint.model.SpanBo;
import com.p.s.pinpoint.model.TransactionMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 调用服务
 */
@Service
@Slf4j
public class RequestServiceImpl implements RequestService {


    private final ApplicationTraceService traceService;

    @Value("${pinpointWebUrl}")
    private String pinpointHost;

    @Value("${history:true}")
    private boolean history;

    private final ApplicationTraceIndexService traceIndexService;


    @Autowired
    public RequestServiceImpl(ApplicationTraceService traceService, ApplicationTraceIndexService traceIndexService) {
        this.traceService = traceService;
        this.traceIndexService = traceIndexService;
    }


    /**
     * 通过RPC名称查询应用请求
     * <ul>
     * <li>如果是http应用，RPC为request_rui</li>
     * <li>如果是RPC应用，RPC为provider method</li>
     * <li>如果是MQ应用，RPC为topic</li>
     * </ul>
     * 支持模糊查询
     *
     * @param app   应用
     * @param query RPC名称
     * @param range 查询开始范围
     * @return RPC名称对应的请求列表
     */
    @Override
    public List<Request> queryRequest(Application app, Range range, RequestQuery query) {

        List<TransactionMetadata> metadataList = traceIndexService.getTraceIndexMetadata(app.getName(), range, query);

        return queryRequest(metadataList, app, range);
    }


    /**
     * 通过RPC名称查询应用请求
     * <ul>
     * <li>如果是http应用，RPC为request_rui</li>
     * <li>如果是RPC应用，RPC为provider method</li>
     * <li>如果是MQ应用，RPC为topic</li>
     * </ul>
     * 支持模糊查询
     *
     * @param app   应用
     * @param query RPC名称
     * @param range 查询开始范围
     * @return RPC名称对应的请求列表
     */
    @Override
    public List<Request> queryRequest4AllRange(Application app, Range range, RequestQuery query) {

        List<TransactionMetadata> metadataList = traceIndexService.getTraceIndexMetadata4AllRange(app.getName(), range, query);

        return queryRequest(metadataList, app, range);
    }


    /**
     * 通过RPC名称查询应用请求
     * <ul>
     * <li>如果是http应用，RPC为request_rui</li>
     * <li>如果是RPC应用，RPC为provider method</li>
     * <li>如果是MQ应用，RPC为topic</li>
     * </ul>
     * 支持模糊查询
     *
     * @return RPC名称对应的请求列表
     */
    public List<Request> queryRequest(List<TransactionMetadata> metadataList, Application app, Range range) {

        List<List<SpanBo>> spanBoList = traceService.querySpanBo(metadataList.stream().map(TransactionMetadata::getTransactionId).collect(Collectors.toList()));

        List<Request> result = new ArrayList<>();

        int index = 0;
        for (List<SpanBo> spans : spanBoList) {
            if (spans.size() == 1) {
                result.add(CommonUtils.trans(pinpointHost, history, app, spans.get(0), range));
            } else {
                TransactionMetadata metadata = metadataList.get(index);

                if(metadata == null) {
                    continue;
                }

                for (SpanBo span : spans) {
                    if (metadata.equals(new TransactionMetadata(span.getTransactionId(), span.getCollectorAcceptTime(), span.getElapsed()))) {
                        result.add(CommonUtils.trans(pinpointHost, history, app, span, range));
                    }
                }

            }

            index++;
        }
        return result;
    }
}
