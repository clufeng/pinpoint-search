package com.p.s.c.mapper;

import com.hbase.dao.extractor.ResultsExtractor;
import com.p.s.c.model.AgentInfo;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgentInfoResultsExtractor implements ResultsExtractor<AgentInfo> {

    @Autowired
    private AgentInfoMapper agentInfoMapper;

    @Override
    public AgentInfo extractData(ResultScanner results) throws Exception {
        int found = 0;
        for (Result result : results) {
            return agentInfoMapper.mapRow(result, found++);
        }
        return null;
    }
}