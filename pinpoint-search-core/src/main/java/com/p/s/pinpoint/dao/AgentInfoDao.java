package com.p.s.pinpoint.dao;

import com.p.s.pinpoint.model.AgentInfo;

import java.util.List;

public interface AgentInfoDao {

    AgentInfo getInitialAgentInfo(String agentId);

    List<AgentInfo> getInitialAgentInfos(List<String> agentIds);

}
