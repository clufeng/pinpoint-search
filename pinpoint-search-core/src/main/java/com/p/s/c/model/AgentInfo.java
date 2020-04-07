package com.p.s.c.model;

import org.apache.commons.lang3.time.FastDateFormat;

public class AgentInfo {

    private static FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss:SSS");

    private String agentId;

    private long startTime;



    private String startTimeStr;

    public AgentInfo() {
    }

    public AgentInfo(String agentId, long startTime) {
        this.agentId = agentId;
        this.startTime = startTime;
        setStartTimeStr(dateFormat.format(this.startTime));
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    @Override
    public String toString() {
        return "AgentInfo{" +
                "agentId='" + agentId + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
