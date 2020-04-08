package com.p.s.pinpoint.serializer;

/**
 * @author Woonduk Kang(emeroad)
 */
public interface RowKeyEncoder<V> {

    byte[] encodeRowKey(V value);

}
