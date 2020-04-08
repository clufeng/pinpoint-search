package com.p.s.core.service;


import com.p.s.pinpoint.model.Application;

import java.util.List;
import java.util.Optional;

/**
 * 应用服务
 * @author clufeng
 */
public interface ApplicationService {

    /**
     * 根据一个用名称获取应用对象，如果应用名称为空，则返回一个空的Optional
     * @param appName 应用名
     * @return 应用对象
     */
    Optional<Application> getApplication(String appName);

    /**
     * 获取全部应用
     * @return 全部应用集合
     */
    List<Application> getApplications();

    /**
     * 根据应用名称集合获取应用对象集合
     * @param appNames 应用名称集合
     * @return 应用对象集合
     */
    List<Application> getApplications(List<String> appNames);

}
