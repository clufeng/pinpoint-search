package com.p.s.c.mapper;

import com.hbase.dao.mapper.RowMapper;
import com.p.s.c.model.AgentInfo;
import com.p.s.c.utils.BytesUtils;
import com.p.s.c.utils.TimeUtils;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.stereotype.Component;

import static com.p.s.c.hbase.HBaseTables.AGENT_NAME_MAX_LEN;

@Component
public class AgentInfoMapper implements RowMapper<AgentInfo> {
    @Override
    public AgentInfo mapRow(Result result, int rowNum) throws Exception {
        byte[] rowKey = result.getRow();
        String agentId = BytesUtils.safeTrim(BytesUtils.toString(rowKey, 0, AGENT_NAME_MAX_LEN));
        long reverseStartTime = BytesUtils.bytesToLong(rowKey, AGENT_NAME_MAX_LEN);
        long startTime = TimeUtils.recoveryTimeMillis(reverseStartTime);
        return new AgentInfo(agentId,startTime);
    }
}
