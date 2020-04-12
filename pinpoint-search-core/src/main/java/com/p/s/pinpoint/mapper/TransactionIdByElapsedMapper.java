package com.p.s.pinpoint.mapper;


import com.hbase.dao.mapper.RowMapper;
import com.p.s.pinpoint.buffer.Buffer;
import com.p.s.pinpoint.buffer.OffsetFixedBuffer;
import com.p.s.pinpoint.model.TransactionId;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TransactionIdByElapsedMapper implements RowMapper<List<TransactionId>> {

    @Value("${elapsed.threshold:1000}")
    private int threshold;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<TransactionId> mapRow(Result result, int rowNum) throws Exception {
        if (result.isEmpty()) {
            return Collections.emptyList();
        }
        Cell[] rawCells = result.rawCells();
        List<TransactionId> traceIdList = new ArrayList<>();
        for (Cell cell : rawCells) {

            final Buffer buffer = new OffsetFixedBuffer(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());

            int elapsed = buffer.readVInt();

            if(elapsed < threshold) {
                continue;
            }

            logger.debug("TransactionIdByElapsedMapper elapsed : {}ms", elapsed);

            final byte[] qualifierArray = cell.getQualifierArray();
            final int qualifierOffset = cell.getQualifierOffset();
            final int qualifierLength = cell.getQualifierLength();
            // increment by value of key
            TransactionId traceId = TransactionIdMapper.parseVarTransactionId(qualifierArray, qualifierOffset, qualifierLength);

            traceIdList.add(traceId);

            logger.debug("found traceId {}", traceId);
        }
        return traceIdList;
    }




}
