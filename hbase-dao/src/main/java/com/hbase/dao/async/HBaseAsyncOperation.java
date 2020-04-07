
package com.hbase.dao.async;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface HBaseAsyncOperation {

    boolean isAvailable();

    boolean put(TableName tableName, final Put put);

    List<Put> put(TableName tableName, final List<Put> puts);

    Long getOpsCount();

    Long getOpsRejectedCount();

    Long getCurrentOpsCount();

    Long getOpsFailedCount();

    Long getOpsAverageLatency();

    Map<String, Long> getCurrentOpsCountForEachRegionServer();

    Map<String, Long> getOpsFailedCountForEachRegionServer();

    Map<String, Long> getOpsAverageLatencyForEachRegionServer();

}