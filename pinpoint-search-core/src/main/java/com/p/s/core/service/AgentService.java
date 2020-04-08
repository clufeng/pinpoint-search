package com.p.s.core.service;

import java.util.List;
import java.util.Map;

/**
 * Agent 服务
 * @author clufeng
 */
public interface AgentService {

    List<String> getAgents(String appName);

    Map<String, List<String>> getAllAgentsMap();

}
