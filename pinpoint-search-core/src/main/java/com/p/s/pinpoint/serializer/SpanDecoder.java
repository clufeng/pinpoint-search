package com.p.s.pinpoint.serializer;


import com.p.s.pinpoint.buffer.Buffer;

/**
 * @author Woonduk Kang(emeroad)
 */
public interface SpanDecoder {
    
    Object UNKNOWN = new Object();

    Object decode(Buffer qualifier, Buffer columnValue, SpanDecodingContext decodingContext);

    void next(SpanDecodingContext decodingContext);

}
