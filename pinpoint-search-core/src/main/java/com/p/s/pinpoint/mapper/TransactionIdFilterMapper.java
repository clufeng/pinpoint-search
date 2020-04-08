package com.p.s.pinpoint.mapper;

import com.p.s.pinpoint.buffer.Buffer;
import com.p.s.pinpoint.buffer.OffsetFixedBuffer;
import com.p.s.pinpoint.model.TransactionId;
import com.p.s.pinpoint.utils.TransactionIdFilter;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TransactionIdFilterMapper extends TransactionIdMapper {

    private Set<String> agentIdSet;

    private String rpcName;

    private boolean hasErr = false;

    private int threshold = 0;

    @Override
    public List<TransactionId> mapRow(Result result, int rowNum) throws Exception {

        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        Cell[] rawCells = result.rawCells();

        List<TransactionId> traceIdList = new ArrayList<>(rawCells.length);

        TransactionIdFilter  filter = new TransactionIdFilter(agentIdSet, rpcName, hasErr, threshold);

        for (Cell cell : rawCells) {

            final byte[] valueArray = cell.getValueArray();
            final int valueOffset = cell.getValueOffset();
            final int valueLength = cell.getValueLength();

            final Buffer valueBuffer = new OffsetFixedBuffer(valueArray, valueOffset, valueLength);

            int elapsed = valueBuffer.readVInt();
            int err = valueBuffer.readSVInt();
            String agentId = valueBuffer.readPrefixedString();

            String rpc;
            try {
                rpc = valueBuffer.readPrefixedString();
            }catch (Exception e) {
                rpc = null;
            }

            if(filter.filter(agentId, rpc, err, elapsed)) {
                continue;
            }

            final byte[] qualifierArray = cell.getQualifierArray();
            final int qualifierOffset = cell.getQualifierOffset();
            final int qualifierLength = cell.getQualifierLength();

            // increment by value of key

            TransactionId traceId = parseVarTransactionId(qualifierArray, qualifierOffset, qualifierLength);
            traceIdList.add(traceId);

        }
        return traceIdList;
    }


    public void setAgentIdSet(Set<String> agentIdSet) {
        this.agentIdSet = agentIdSet;
    }


    public void setRpcName(String rpcName) {
        this.rpcName = rpcName;
    }


    public void setHasErr(boolean hasErr) {
        this.hasErr = hasErr;
    }


    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
