package com.p.s.core.service;


import com.p.s.core.model.Request;
import com.p.s.core.model.RequestQuery;
import com.p.s.pinpoint.model.Application;
import com.p.s.pinpoint.model.Range;

import java.util.List;

/**
 * 请求服务
 * @author clufeng
 */
public interface RequestService {

    /**
     * 通过RPC名称查询应用请求
     * <ul>
     * <li>如果是http应用，RPC为request_rui</li>
     * <li>如果是RPC应用，RPC为provider method</li>
     * <li>如果是MQ应用，RPC为topic</li>
     * </ul>
     * 支持模糊查询
     * @param app 应用
     * @param query RPC名称
     * @param range 查询开始范围
     * @return RPC名称对应的请求列表
     */
    List<Request> queryRequest(Application app, Range range, RequestQuery query);

    /**
     * 通过RPC名称查询应用请求,会查找全部的Range内的请求
     * <ul>
     * <li>如果是http应用，RPC为request_rui</li>
     * <li>如果是RPC应用，RPC为provider method</li>
     * <li>如果是MQ应用，RPC为topic</li>
     * </ul>
     * 支持模糊查询
     * @param app 应用
     * @param query RPC名称
     * @param range 查询开始范围
     * @return RPC名称对应的请求列表
     */
    List<Request> queryRequest4AllRange(Application app, Range range, RequestQuery query);
}
