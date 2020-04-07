package com.p.s.c.serializer;


import com.p.s.c.model.SpanChunkBo;
import org.apache.hadoop.hbase.client.Put;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

import static com.p.s.c.hbase.HBaseTables.TRACE_V2_CF_SPAN;


/**
 * @author Woonduk Kang(emeroad)
 */
@Component
public class SpanChunkSerializerV2 implements HbaseSerializer<SpanChunkBo, Put> {

    private final SpanEncoder spanEncoder = new SpanEncoderV0();

    @Override
    public void serialize(SpanChunkBo spanChunkBo, Put put, SerializationContext context) {
        if (spanChunkBo == null) {
            throw new NullPointerException("spanChunkBo must not be null");
        }

        SpanEncodingContext<SpanChunkBo> encodingContext = new SpanEncodingContext<SpanChunkBo>(spanChunkBo);

        ByteBuffer qualifier = spanEncoder.encodeSpanChunkQualifier(encodingContext);
        ByteBuffer columnValue = spanEncoder.encodeSpanChunkColumnValue(encodingContext);

        long acceptedTime = put.getTimeStamp();
        put.addColumn(TRACE_V2_CF_SPAN, qualifier, acceptedTime, columnValue);

    }

}
