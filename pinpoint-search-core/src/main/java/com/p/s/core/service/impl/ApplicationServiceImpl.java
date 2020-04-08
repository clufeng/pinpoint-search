package com.p.s.core.service.impl;

import com.p.s.pinpoint.dao.ApplicationIndexDao;
import com.p.s.pinpoint.model.Application;
import com.p.s.core.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * 应用服务实现类
 * @author clufeng
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationIndexDao appDao;

    @Autowired
    public ApplicationServiceImpl(ApplicationIndexDao appDao) {
        this.appDao = appDao;
    }

    public List<Application> getApplications() {
        return appDao.selectAllApplicationNames();
    }

    @Override
    public List<Application> getApplications(List<String> appNames) {
        return appDao.selectApplication(appNames);
    }

    public Optional<Application> getApplication(String appName) {

        if(StringUtils.isEmpty(appName)) {
            return Optional.empty();
        }

        return Optional.ofNullable(appDao.selectApplication(appName));
    }

}
