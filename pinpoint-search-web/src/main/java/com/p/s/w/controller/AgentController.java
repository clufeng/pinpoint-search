package com.p.s.w.controller;


import com.p.s.pinpoint.model.Application;
import com.p.s.core.service.AgentService;
import com.p.s.core.service.ApplicationService;
import com.p.s.w.api.ApiResponse;
import com.p.s.w.api.ListJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api")
public class AgentController extends BasicController {

    private final AgentService agentService;

    private final ApplicationService applicationService;

    @Autowired
    public AgentController(AgentService agentService, ApplicationService applicationService) {
        this.agentService = agentService;
        this.applicationService = applicationService;
    }

    /**
     * 获取接入应用所有的Agents集合
     * @param appName 应用名称
     * @return 所有Agent的ID集合
     */
    @RequestMapping("/{appName}/agents")
    public ApiResponse<ListJson<String>> getAgents(@PathVariable String appName) {

        log.debug("getAgents appName:{}", appName);

        return successListJson(agentService.getAgents(appName));
    }

    /**
     * 获取Agent ID集合
     * @return Agent ID集合
     */
    @RequestMapping("/agents")
    public ApiResponse<ListJson<String>> getAgents() {
        List<String> result = new ArrayList<>();
        agentService.getAllAgentsMap().values().forEach(result::addAll);
        return successListJson(result);
    }


    /**
     * 根据ip查找应用
     * @param agentId agentId
     * @return 应用对象
     */
    @RequestMapping("/agent/query_app")
    public ApiResponse<Application> getApplication(String agentId) {
        Optional<Application> op = applicationService.getApplications().
                stream().
                filter(app -> agentService.getAgents(app.getName()).contains(agentId)).findFirst();
        return success(op.orElse(null));
    }

}
