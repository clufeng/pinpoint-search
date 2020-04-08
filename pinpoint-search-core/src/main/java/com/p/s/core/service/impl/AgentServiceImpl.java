package com.p.s.core.service.impl;


import com.p.s.pinpoint.dao.ApplicationIndexDao;
import com.p.s.core.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AgentServiceImpl implements AgentService {

    private final ApplicationIndexDao dao;

    @Autowired
    public AgentServiceImpl(ApplicationIndexDao dao) {
        this.dao = dao;
    }


    @Override
    public List<String> getAgents(String appName) {
        List<String> result = new ArrayList<>();
        if (!StringUtils.isEmpty(appName)) {
            result.addAll(dao.selectAgentIds(appName));
        }
        return result;
    }

    @Override
    public Map<String, List<String>> getAllAgentsMap() {
        Map<String, List<String>> result = new HashMap<>();

        dao.selectAllApplicationNames()
                .forEach(app -> result.put(app.getName(), getAgents(app.getName())));

        return result;
    }


}
