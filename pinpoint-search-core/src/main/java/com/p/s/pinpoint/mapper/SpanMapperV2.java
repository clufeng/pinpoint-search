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

import com.google.common.annotations.Beta;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.hbase.dao.mapper.RowMapper;
import com.p.s.pinpoint.buffer.Buffer;
import com.p.s.pinpoint.buffer.OffsetFixedBuffer;
import com.p.s.pinpoint.hbase.HBaseTables;
import com.p.s.pinpoint.model.*;
import com.p.s.pinpoint.model.*;
import com.p.s.pinpoint.serializer.RowKeyDecoder;
import com.p.s.pinpoint.serializer.SpanDecoder;
import com.p.s.pinpoint.serializer.SpanDecoderV0;
import com.p.s.pinpoint.serializer.SpanDecodingContext;
import com.p.s.pinpoint.thrift.util.SpanVersion;
import com.p.s.pinpoint.utils.SpanEventComparator;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author emeroad
 */
@Beta
@Component
public class SpanMapperV2 implements RowMapper<List<SpanBo>> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SpanDecoder spanDecoder = new SpanDecoderV0();

    private final RowKeyDecoder<TransactionId> rowKeyDecoder;

    @Autowired
    public SpanMapperV2(@Qualifier("traceRowKeyDecoderV2") RowKeyDecoder<TransactionId> rowKeyDecoder) {
        if (rowKeyDecoder == null) {
            throw new NullPointerException("rowKeyDecoder must not be null");
        }

        this.rowKeyDecoder = rowKeyDecoder;
    }

    @Override
    public List<SpanBo> mapRow(Result result, int rowNum) throws Exception {
        if (result.isEmpty()) {
            return Collections.emptyList();
        }


        byte[] rowKey = result.getRow();
        final TransactionId transactionId = this.rowKeyDecoder.decodeRowKey(rowKey);

        final Cell[] rawCells = result.rawCells();

        ListMultimap<AgentKey, SpanBo> spanMap = LinkedListMultimap.create();
        List<SpanChunkBo> spanChunkList = new ArrayList<>();

        final SpanDecodingContext decodingContext = new SpanDecodingContext();
        decodingContext.setTransactionId(transactionId);

        for (Cell cell : rawCells) {
            SpanDecoder spanDecoder = null;
            // only if family name is "span"
            if (CellUtil.matchingFamily(cell, HBaseTables.TRACE_V2_CF_SPAN)) {

                decodingContext.setCollectorAcceptedTime(cell.getTimestamp());

                final Buffer qualifier = new OffsetFixedBuffer(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                final Buffer columnValue = new OffsetFixedBuffer(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());

                spanDecoder = resolveDecoder(columnValue);
                final Object decodeObject = spanDecoder.decode(qualifier, columnValue, decodingContext);
                if (decodeObject instanceof SpanBo) {
                    SpanBo spanBo = (SpanBo) decodeObject;
                    if (logger.isDebugEnabled()) {
                        logger.debug("spanBo:{}", spanBo);
                    }
                    AgentKey agentKey = newAgentKey(spanBo);
                    spanMap.put(agentKey, spanBo);
                } else if (decodeObject instanceof SpanChunkBo) {
                    SpanChunkBo spanChunkBo = (SpanChunkBo) decodeObject;
                    if (logger.isDebugEnabled()) {
                        logger.debug("spanChunkBo:{}", spanChunkBo);
                    }
                    spanChunkList.add(spanChunkBo);
                }

            } else {
                logger.warn("Unknown ColumnFamily :{}", Bytes.toStringBinary(CellUtil.cloneFamily(cell)));
            }
            nextCell(spanDecoder, decodingContext);
        }
        decodingContext.finish();


        return buildSpanBoList(spanMap, spanChunkList);

    }

    private void nextCell(SpanDecoder spanDecoder, SpanDecodingContext decodingContext) {
        if (spanDecoder != null) {
            spanDecoder.next(decodingContext);
        } else {
            decodingContext.next();
        }
    }

    private SpanDecoder resolveDecoder(Buffer columnValue) {
        final byte version = columnValue.getByte(0);
        if (SpanVersion.supportedVersionRange(version)) {
            return this.spanDecoder;
        } else {
            throw new IllegalStateException("unsupported version");
        }
    }


    private List<SpanBo> buildSpanBoList(ListMultimap<AgentKey, SpanBo> spanMap, List<SpanChunkBo> spanChunkList) {
        List<SpanBo> spanBoList = bindSpanChunk(spanMap, spanChunkList);
        sortSpanEvent(spanBoList);
        return spanBoList;
    }


    private void sortSpanEvent(List<SpanBo> spanBoList) {
        for (SpanBo spanBo : spanBoList) {
            List<SpanEventBo> spanEventBoList = spanBo.getSpanEventBoList();
            Collections.sort(spanEventBoList, SpanEventComparator.INSTANCE);
        }
    }

    private List<SpanBo> bindSpanChunk(ListMultimap<AgentKey, SpanBo> spanMap, List<SpanChunkBo> spanChunkList) {
        for (SpanChunkBo spanChunkBo : spanChunkList) {
            AgentKey agentKey = newAgentKey(spanChunkBo);
            List<SpanBo> matchedSpanBoList = spanMap.get(agentKey);
            if (matchedSpanBoList != null) {
                final int spanIdCollisionSize = matchedSpanBoList.size();
                if (spanIdCollisionSize > 1) {
                    // exceptional case dump
                    logger.warn("spanIdCollision {}", matchedSpanBoList);
                }

                int agentLevelCollisionCount = 0;
                for (SpanBo spanBo : matchedSpanBoList) {
                    if (spanBo.getAgentId().equals(spanChunkBo.getAgentId())) {
                        spanBo.addSpanEventBoList(spanChunkBo.getSpanEventBoList());
                        agentLevelCollisionCount++;
                    }
                }
                if (agentLevelCollisionCount > 1) {
                    // exceptional case dump
                    logger.warn("agentLevelCollision {}", matchedSpanBoList);
                }
            } else {
                if (logger.isInfoEnabled()) {
                    logger.info("Span not exist spanId:{} spanChunk:{}", agentKey, spanChunkBo);
                }
            }
        }
        return Lists.newArrayList(spanMap.values());
    }

    private AgentKey newAgentKey(BasicSpan basicSpan) {
        return new AgentKey(basicSpan.getApplicationId(), basicSpan.getAgentId(), basicSpan.getAgentStartTime(), basicSpan.getSpanId());
    }

    public static class AgentKey {
        private final long spanId;
        private final String applicationId;
        private final String agentId;
        private final long agentStartTime;


        public AgentKey(String applicationId, String agentId, long agentStartTime, long spanId) {
            if (applicationId == null) {
                throw new NullPointerException("applicationId must not be null");
            }
            if (agentId == null) {
                throw new NullPointerException("agentId must not be null");
            }
            this.applicationId = applicationId;
            this.agentId = agentId;
            this.agentStartTime = agentStartTime;
            this.spanId = spanId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AgentKey agentKey = (AgentKey) o;

            if (spanId != agentKey.spanId) return false;
            if (agentStartTime != agentKey.agentStartTime) return false;
            if (!applicationId.equals(agentKey.applicationId)) return false;
            return agentId.equals(agentKey.agentId);

        }

        @Override
        public int hashCode() {
            int result = (int) (spanId ^ (spanId >>> 32));
            result = 31 * result + applicationId.hashCode();
            result = 31 * result + agentId.hashCode();
            result = 31 * result + (int) (agentStartTime ^ (agentStartTime >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "AgentKey{" +
                    "spanId=" + spanId +
                    ", applicationId='" + applicationId + '\'' +
                    ", agentId='" + agentId + '\'' +
                    ", agentStartTime=" + agentStartTime +
                    '}';
        }
    }
}
