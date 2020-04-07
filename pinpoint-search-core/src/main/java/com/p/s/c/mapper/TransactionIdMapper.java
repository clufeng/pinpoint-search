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

package com.p.s.c.mapper;


import com.hbase.dao.mapper.RowMapper;
import com.p.s.c.buffer.Buffer;
import com.p.s.c.buffer.OffsetFixedBuffer;
import com.p.s.c.model.TransactionId;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author emeroad
 * @author netspider
 */
@Component
public class TransactionIdMapper implements RowMapper<List<TransactionId>> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public List<TransactionId> mapRow(Result result, int rowNum) throws Exception {
        if (result.isEmpty()) {
            return Collections.emptyList();
        }
        Cell[] rawCells = result.rawCells();
        List<TransactionId> traceIdList = new ArrayList<>(rawCells.length);
        if(rawCells.length > 0) {
            Cell cell = rawCells[0];
            final byte[] qualifierArray = cell.getQualifierArray();
            final int qualifierOffset = cell.getQualifierOffset();
            final int qualifierLength = cell.getQualifierLength();
            // increment by value of key
            TransactionId traceId = parseVarTransactionId(qualifierArray, qualifierOffset, qualifierLength);
            traceIdList.add(traceId);

            logger.debug("found traceId {}", traceId);
        }
        return traceIdList;
    }

    public static TransactionId parseVarTransactionId(byte[] bytes, int offset, int length) {
        if (bytes == null) {
            throw new NullPointerException("bytes must not be null");
        }
        final Buffer buffer = new OffsetFixedBuffer(bytes, offset, length);
        
        // skip elapsed time (not used) hbase column prefix - only used for filtering.
        // Not sure if we can reduce the data size any further.
        // buffer.readInt();
        
        String agentId = buffer.readPrefixedString();
        long agentStartTime = buffer.readSVLong();
        long transactionSequence = buffer.readVLong();
        return new TransactionId(agentId, agentStartTime, transactionSequence);
    }
}
