package com.p.s.pinpoint.dao;

import com.hbase.dao.HBaseOperations;
import com.p.s.pinpoint.hbase.HBaseTables;
import com.p.s.pinpoint.mapper.AgentInfoResultsExtractor;
import com.p.s.pinpoint.model.AgentInfo;
import com.p.s.pinpoint.utils.RowKeyUtils;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class HbaseAgentInfoDao implements AgentInfoDao {

    private static final int SCANNER_CACHING = 1;

    @Autowired
    private HBaseOperations hbaseOperations2;

    @Autowired
    private AgentInfoResultsExtractor agentInfoResultsExtractor;

    @Override
    public AgentInfo getInitialAgentInfo(final String agentId) {
        if (agentId == null) {
            throw new NullPointerException("agentId must not be null");
        }
        Scan scan = createScanForInitialAgentInfo(agentId);
        return this.hbaseOperations2.find(HBaseTables.AGENTINFO, scan, agentInfoResultsExtractor);
    }

    @Override
    public List<AgentInfo> getInitialAgentInfos(List<String> agentIds) {
        if (agentIds == null || agentIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<Scan> scans = new ArrayList<>(agentIds.size());
        for (String agentId : agentIds) {
            scans.add(createScanForInitialAgentInfo(agentId));
        }
        return this.hbaseOperations2.find(HBaseTables.AGENTINFO, scans, agentInfoResultsExtractor);
    }

    private Scan createScanForInitialAgentInfo(String agentId) {
        Scan scan = new Scan();
        byte[] agentIdBytes = Bytes.toBytes(agentId);
        byte[] reverseStartKey = RowKeyUtils.concatFixedByteAndLong(agentIdBytes, HBaseTables.AGENT_NAME_MAX_LEN, 1499443200L);
        scan.setStartRow(reverseStartKey);
//        scan.setReversed(true);
        scan.setMaxVersions(1);
        scan.setCaching(SCANNER_CACHING);
        return scan;
    }

}
