package com.p.s.c.dao;

import com.p.s.c.model.AgentInfo;

import java.util.List;

public interface AgentInfoDao {

    AgentInfo getInitialAgentInfo(String agentId);

    List<AgentInfo> getInitialAgentInfos(List<String> agentIds);

}
