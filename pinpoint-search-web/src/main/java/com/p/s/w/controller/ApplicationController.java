package com.p.s.w.controller;


import com.p.s.pinpoint.model.Application;
import com.p.s.core.service.ApplicationService;
import com.p.s.w.api.ApiResponse;
import com.p.s.w.api.ListJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApplicationController extends BasicController{

    /**
     * 应用服务
     */
    private final ApplicationService service;

    @Autowired
    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @RequestMapping("/apps")
    public ApiResponse<ListJson<Application>> getApplications() {
        return success(new ListJson<>(service.getApplications()));
    }


    @RequestMapping("/apps/{appName}")
    public ApiResponse<Application> getApplication(@PathVariable String appName) {
        return success(service.getApplication(appName).orElse(null));
    }

}
