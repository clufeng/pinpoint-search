package com.p.s.w.controller;


import com.p.s.core.model.Request;
import com.p.s.core.model.RequestQuery;
import com.p.s.core.service.ApplicationService;
import com.p.s.core.service.RequestService;
import com.p.s.core.service.impl.ApplicationServiceImpl;
import com.p.s.core.utils.CommonUtils;
import com.p.s.pinpoint.model.Range;
import com.p.s.w.api.ApiResponse;
import com.p.s.w.api.ListJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/api")
@Slf4j
public class RequestController extends BasicController {

    private final RequestService requestService;

    private final ApplicationService applicationService;

    @Value("${query.time.max.hour}")
    private int maxHour;

    @Value("${elapsed.threshold}")
    private int threshold;

    @Autowired
    public RequestController(RequestService requestService, ApplicationServiceImpl applicationService) {
        this.requestService = requestService;
        this.applicationService = applicationService;
    }

    @PostMapping(value = "/{appName}/requests")
    public ApiResponse<ListJson<Request>> getRequest(@PathVariable String appName,
                                                     @RequestParam(required = false) String rpcName,
                                                     @RequestParam(required = false) Set<String> agentIds,
                                                     @RequestParam(required = false, defaultValue = "0") int threshold,
                                                     @RequestParam(required = false, defaultValue = "0") int hasErr,
                                                     @RequestParam(required = false, defaultValue = "0") int hour,
                                                     @RequestParam(required = false, defaultValue = "0") long from,
                                                     @RequestParam(required = false, defaultValue = "0") long to) {

        log.info("/{}/requests, rpcName:{}, agentIds:{}, threshold:{}, hour:{}, from:{}, to:{}", appName, rpcName, agentIds, threshold, hour, from, to);

        if(hour == 0 && from == 0 && to == 0) {
            return successListJson(Collections.emptyList());
        }

        RequestQuery query = RequestQuery.newBuilder()
                .agentIdSet(agentIds)
                .rpcName(rpcName)
                .hasErr(hasErr)
                .threshold(threshold)
                .build();

        return successListJson(applicationService.getApplication(appName)
                .map(app -> requestService.queryRequest(app, getRange(hour, from, to), query))
                .orElse(Collections.emptyList()));
    }


    @RequestMapping(value = "/{appName}/all_range_requests")
    public ApiResponse<ListJson<Request>> getAllRangeRequest(@PathVariable String appName,
                                                 @RequestParam(required = false) String rpcName,
                                                 @RequestParam(required = false) Set<String> agentIds,
                                                 @RequestParam(required = false, defaultValue = "0") int threshold,
                                                 @RequestParam(required = false, defaultValue = "0") int hasErr,
                                                 @RequestParam(required = false, defaultValue = "0") int hour,
                                                 @RequestParam(required = false, defaultValue = "0") long from,
                                                 @RequestParam(required = false, defaultValue = "0") long to) {

        log.info("/{}/all_range_requests, rpcName:{}, agentIds:{}, threshold:{}, hour:{}, from:{}, to:{}", appName, rpcName, agentIds, threshold, hour, from, to);

        if(hour == 0 && from == 0 && to == 0) {
            return successListJson(Collections.emptyList());
        }

        RequestQuery query = RequestQuery.newBuilder()
                .agentIdSet(agentIds)
                .rpcName(rpcName)
                .hasErr(hasErr)
                .threshold(threshold)
                .build();

        return successListJson(applicationService.getApplication(appName)
                .map(app -> requestService.queryRequest4AllRange(app, getRange(hour, from, to), query))
                .orElse(Collections.emptyList()));
    }















    @RequestMapping(value = "/{appName}/slow_requests")
    public ApiResponse<ListJson<Request>> getSlowRequest(@PathVariable String appName,
                                        @RequestParam(required = false) String rpcName,
                                        @RequestParam(required = false) Set<String> ips,
                                        @RequestParam(required = false, defaultValue = "0") int threshold,
                                        @RequestParam(required = false, defaultValue = "0") int hour,
                                        @RequestParam(required = false, defaultValue = "0") long from,
                                        @RequestParam(required = false, defaultValue = "0") long to) {

        log.info("/{}/slow_requests, rpcName:{}, hour:{}, from:{}, to:{}", appName, rpcName, hour, from, to);

        if(hour == 0 && from == 0 && to == 0) {
            return successEmptyListJson();
        }

        if(threshold <= 0) {
            threshold = this.threshold;
        }

        RequestQuery query = RequestQuery.newBuilder()
                .agentIdSet(ips)
                .rpcName(rpcName)
                .threshold(threshold)
                .build();

        return successListJson(applicationService.getApplication(appName)
                .map(app -> requestService.queryRequest(app, getRange(hour, from, to), query))
                .orElse(Collections.emptyList()));
    }


    private Range getRange(int hour, long from, long to) {

        if(hour > maxHour) {
            hour = maxHour;
        }

        return CommonUtils.createRange(from, to, hour);
    }

    private Range getRangeByMinute(int minute, long from, long to) {

        if(minute > maxHour * 60) {
            minute = maxHour * 60;
        }

        return CommonUtils.createRangeByMinute(from, to, minute);
    }

}
