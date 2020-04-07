
package com.hbase.dao.async;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;

/**
 *
 */
public class HBaseAsyncOperationFactory {

    public static final String ENABLE_ASYNC_METHOD = "hbase.client.async.enable";
    public static final boolean DEFAULT_ENABLE_ASYNC_METHOD = false;

    public static final String ASYNC_IN_QUEUE_SIZE = "hbase.client.async.in.queuesize";
    public static final int DEFAULT_ASYNC_IN_QUEUE_SIZE = 10000;

    public static final String ASYNC_PERIODIC_FLUSH_TIME = "hbase.tablemultiplexer.flush.period.ms";
    public static final int DEFAULT_ASYNC_PERIODIC_FLUSH_TIME = 100;

    public static final String ASYNC_RETRY_COUNT = "hbase.client.max.retries.in.queue";
    public static final int DEFAULT_ASYNC_RETRY_COUNT = 10;

    public static HBaseAsyncOperation create(Configuration configuration) throws IOException {
        boolean enableAsyncMethod = configuration.getBoolean(ENABLE_ASYNC_METHOD, DEFAULT_ENABLE_ASYNC_METHOD);
        if (!enableAsyncMethod) {
            return DisabledHBaseAsyncOperation.INSTANCE;
        }

        int queueSize = configuration.getInt(ASYNC_IN_QUEUE_SIZE, DEFAULT_ASYNC_IN_QUEUE_SIZE);

        if (configuration.get(ASYNC_PERIODIC_FLUSH_TIME, null) == null) {
            configuration.setInt(ASYNC_PERIODIC_FLUSH_TIME, DEFAULT_ASYNC_PERIODIC_FLUSH_TIME);
        }

        if (configuration.get(ASYNC_RETRY_COUNT, null) == null) {
            configuration.setInt(ASYNC_RETRY_COUNT, DEFAULT_ASYNC_RETRY_COUNT);
        }

        return new HBaseAsyncTemplate(configuration, queueSize);
    }

    public static HBaseAsyncOperation create(Connection connection, Configuration configuration) throws IOException {
        boolean enableAsyncMethod = configuration.getBoolean(ENABLE_ASYNC_METHOD, DEFAULT_ENABLE_ASYNC_METHOD);
        if (!enableAsyncMethod) {
            return DisabledHBaseAsyncOperation.INSTANCE;
        }

        int queueSize = configuration.getInt(ASYNC_IN_QUEUE_SIZE, DEFAULT_ASYNC_IN_QUEUE_SIZE);

        if (configuration.get(ASYNC_PERIODIC_FLUSH_TIME, null) == null) {
            configuration.setInt(ASYNC_PERIODIC_FLUSH_TIME, DEFAULT_ASYNC_PERIODIC_FLUSH_TIME);
        }

        if (configuration.get(ASYNC_RETRY_COUNT, null) == null) {
            configuration.setInt(ASYNC_RETRY_COUNT, DEFAULT_ASYNC_RETRY_COUNT);
        }

        return new HBaseAsyncTemplate(connection, configuration, queueSize);
    }

}