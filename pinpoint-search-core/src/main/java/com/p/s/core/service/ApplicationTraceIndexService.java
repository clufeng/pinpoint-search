package com.p.s.core.service;


import com.p.s.core.model.RequestQuery;
import com.p.s.pinpoint.model.Range;
import com.p.s.pinpoint.model.TransactionId;
import com.p.s.pinpoint.model.TransactionMetadata;

import java.util.List;

/**
 * 应用Trace索引服务
 * <pre>
 * 主要是根据时间范围内查询该应用的请求对应的索引。
 * 索引的内容：
 *      1. rpcName:应用请求的名称，不同请求内容不同:
 *          a: RPC请求对应的rpcName是Provider.method;
 *          b: MQ请求对应的rpcName是JQM Topic;
 *          c: HTTP请求对应的rpcName是request.getUri;
 *      2. threshold:
 *          请求所花费的时间
 *      3. hasErr:
 *          请求是否有异常，用于专门查找异常的请求
 *      4. agentIdSet:
 *          agentId是用于查找某一台或者某几台机器的请求
 * </pre>
 *
 * @author clufeng
 *
 */
public interface ApplicationTraceIndexService {

    /**
     * 查询请求的Transaction id集合
     * @param appName 应用名称
     * @param range 查询开始范围
     * @param query 查询参数
     * @return 索引列表
     */
    List<TransactionId> getTraceIndex(String appName, Range range, RequestQuery query);

    List<TransactionMetadata> getTraceIndexMetadata(String appName, Range range, RequestQuery query);

    List<TransactionMetadata> getTraceIndexMetadata4AllRange(String appName, Range range, RequestQuery query);

}
