package com.p.s.pinpoint.mapper;

import com.hbase.dao.mapper.RowMapper;
import com.p.s.pinpoint.model.AgentInfo;
import com.p.s.pinpoint.utils.BytesUtils;
import com.p.s.pinpoint.utils.TimeUtils;
import com.p.s.pinpoint.hbase.HBaseTables;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.stereotype.Component;

@Component
public class AgentInfoMapper implements RowMapper<AgentInfo> {
    @Override
    public AgentInfo mapRow(Result result, int rowNum) throws Exception {
        byte[] rowKey = result.getRow();
        String agentId = BytesUtils.safeTrim(BytesUtils.toString(rowKey, 0, HBaseTables.AGENT_NAME_MAX_LEN));
        long reverseStartTime = BytesUtils.bytesToLong(rowKey, HBaseTables.AGENT_NAME_MAX_LEN);
        long startTime = TimeUtils.recoveryTimeMillis(reverseStartTime);
        return new AgentInfo(agentId,startTime);
    }
}
