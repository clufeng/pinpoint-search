package com.p.s.c.serializer;


import com.p.s.c.buffer.Buffer;

/**
 * @author Woonduk Kang(emeroad)
 */
public interface SpanDecoder {
    
    Object UNKNOWN = new Object();

    Object decode(Buffer qualifier, Buffer columnValue, SpanDecodingContext decodingContext);

    void next(SpanDecodingContext decodingContext);

}
