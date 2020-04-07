
package com.hbase.dao.handler;

import org.apache.hadoop.hbase.client.Result;

/**
 *
 */
public interface LimitEventHandler {
    void handleLastResult(Result lastResult);
}
