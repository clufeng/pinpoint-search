package com.p.s.core.service.impl;


import com.p.s.core.service.ApplicationTraceService;
import com.p.s.pinpoint.dao.TraceDao;
import com.p.s.pinpoint.model.SpanBo;
import com.p.s.pinpoint.model.TransactionId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ApplicationTraceServiceImpl implements ApplicationTraceService {

    private final TraceDao traceDao;

    @Autowired
    public ApplicationTraceServiceImpl(TraceDao traceDao) {
        this.traceDao = traceDao;
    }

    /**
     * 查询索引对应的请求
     * @param tidList 索引列表
     * @return 索引对应的请求列表
     */
    public List<List<SpanBo>> querySpanBo(List<TransactionId> tidList) {
        if(CollectionUtils.isEmpty(tidList)) {
            return Collections.emptyList();
        }

        log.info("query span start");

        List<List<SpanBo>> lists = traceDao.selectSpans(tidList);

        log.info("query span success ! span size : {}", lists.size());

        return lists;
    }
}
