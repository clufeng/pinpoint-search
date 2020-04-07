package com.hbase.dao.handler;

import org.apache.hadoop.hbase.client.Result;

/**
 *
 */
public class EmptyLimitEventHandler implements LimitEventHandler{

    @Override
    public void handleLastResult(Result lastResult) {
    }
}
