package com.p.s.c.tracker;

import org.apache.hadoop.hbase.Cell;

/**
 * @author Woonduk Kang(emeroad)
 */
public interface CellTracker {

    void trace(Cell cell);

    void log();
}
