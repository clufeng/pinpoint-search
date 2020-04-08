package com.p.s.pinpoint.serializer;

/**
 * @author Woonduk Kang(emeroad)
 */
public interface RowKeyDecoder<V> {

    V decodeRowKey(byte[] rowkey);

}
