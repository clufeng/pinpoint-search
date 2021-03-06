package com.p.s.pinpoint.serializer;

import org.apache.hadoop.hbase.client.Mutation;

/**
 * @author Woonduk Kang(emeroad)
 */
public interface HbaseSerializer<T, M extends Mutation> {

    void serialize(T value, M mutation, SerializationContext context);

}
