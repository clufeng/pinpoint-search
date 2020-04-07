package com.p.s.c.serializer;

/**
 * @author Woonduk Kang(emeroad)
 */
public interface RowKeyEncoder<V> {

    byte[] encodeRowKey(V value);

}
