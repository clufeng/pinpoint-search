package com.hbase.dao;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Table;

/**
 * Defines methods to create new HTableInterface.
 */
public interface TableFactory {

  /**
   * Creates a new HTableInterface.
   *
   * @param tableName name of the HBase table.
   * @return HTableInterface instance.
   */
  Table getTable(TableName tableName);


  /**
   * Release the HTable resource represented by the table.
   * @param table
   */
  void releaseTable(final Table table);
}
