/*
 * Copyright 2014 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.p.s.pinpoint.mapper;


import com.hbase.dao.mapper.RowMapper;
import com.p.s.pinpoint.buffer.Buffer;
import com.p.s.pinpoint.buffer.OffsetFixedBuffer;
import com.p.s.pinpoint.model.TransactionId;
import com.p.s.pinpoint.model.TransactionMetadata;
import com.p.s.pinpoint.utils.BytesUtils;
import com.p.s.pinpoint.utils.TimeUtils;
import com.p.s.pinpoint.utils.TransactionIdFilter;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.p.s.pinpoint.hbase.HBaseTables.APPLICATION_NAME_MAX_LEN;
import static com.p.s.pinpoint.hbase.HBaseTables.APPLICATION_TRACE_INDEX_ROW_DISTRIBUTE_SIZE;

/**
 * @author emeroad
 * @author netspider
 */
public class TransactionMetadataMapper implements RowMapper<List<TransactionMetadata>> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Set<String> agentIdSet;

    private String rpcName;

    private boolean hasErr = false;

    private int threshold = 0;

    public TransactionMetadataMapper(Set<String> agentIdSet, String rpcName, boolean hasErr, int threshold) {
        this.agentIdSet = agentIdSet;
        this.rpcName = rpcName;
        this.hasErr = hasErr;
        this.threshold = threshold;
    }

    @Override
    public List<TransactionMetadata> mapRow(Result result, int rowNum) throws Exception {
        if (result.isEmpty()) {
            return Collections.emptyList();
        }
        Cell[] rawCells = result.rawCells();
        List<TransactionMetadata> traceIdList = new ArrayList<>(rawCells.length);
        TransactionIdFilter filter = new TransactionIdFilter(agentIdSet, rpcName, hasErr, threshold);

        for (Cell cell : rawCells) {
            try {

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
                    return null;
                }

                traceIdList.add(createTransactionMetadata(cell));
            } catch (Exception e) {
                break;
            }

        }


        return traceIdList;
    }

    private TransactionMetadata createTransactionMetadata(Cell cell) {
        final Buffer valueBuffer = new OffsetFixedBuffer(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
        int elapsed = valueBuffer.readVInt();

        int exceptionCode = valueBuffer.readSVInt();
        String agentId = valueBuffer.readPrefixedString();

        long reverseAcceptedTime = BytesUtils.bytesToLong(cell.getRowArray(), cell.getRowOffset() + APPLICATION_NAME_MAX_LEN + APPLICATION_TRACE_INDEX_ROW_DISTRIBUTE_SIZE);
        long acceptedTime = TimeUtils.recoveryTimeMillis(reverseAcceptedTime);

        // TransactionId transactionId = new TransactionId(buffer,
        // qualifierOffset);

        // for temporary, used TransactionIdMapper
        TransactionId transactionId = TransactionIdMapper.parseVarTransactionId(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());

        return new TransactionMetadata(transactionId, acceptedTime, elapsed);
    }
}
