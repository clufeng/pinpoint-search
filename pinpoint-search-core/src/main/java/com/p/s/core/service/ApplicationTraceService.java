package com.p.s.core.service;


import com.p.s.pinpoint.model.SpanBo;
import com.p.s.pinpoint.model.TransactionId;

import java.util.List;

/**
 * 应用Trace服务
 * @author clufeng
 */
public interface ApplicationTraceService {

    /**
     * 查询索引对应的请求
     * @param tidList 索引列表
     * @return 索引对应的请求列表
     */
    List<List<SpanBo>> querySpanBo(List<TransactionId> tidList);

}
