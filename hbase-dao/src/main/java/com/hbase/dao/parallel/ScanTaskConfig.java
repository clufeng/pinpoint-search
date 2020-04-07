
package com.hbase.dao.parallel;

import com.hbase.dao.HBaseAccessor;
import com.hbase.dao.TableFactory;
import com.sematext.hbase.wd.AbstractRowKeyDistributor;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;

import java.nio.charset.Charset;

/**
 *
 */
public class ScanTaskConfig {

    private final TableName tableName;
    private final Configuration configuration;
    private final Charset charset;
    private final TableFactory tableFactory;

    private final AbstractRowKeyDistributor rowKeyDistributor;
    private final int scanTaskQueueSize;

    public ScanTaskConfig(TableName tableName, HBaseAccessor hbaseAccessor, AbstractRowKeyDistributor rowKeyDistributor, int scanCaching) {
        this(tableName, hbaseAccessor.getConfiguration(), hbaseAccessor.getCharset(), hbaseAccessor.getTableFactory(), rowKeyDistributor, scanCaching);
    }

    public ScanTaskConfig(TableName tableName, Configuration configuration, Charset charset, TableFactory tableFactory, AbstractRowKeyDistributor rowKeyDistributor, int scanCaching) {
        if (tableName == null) {
            throw new NullPointerException("No table specified");
        }
        if (rowKeyDistributor == null) {
            throw new NullPointerException("rowKeyDistributor must not be null");
        }
        this.tableName = tableName;
        this.configuration = configuration;
        this.charset = charset;
        this.tableFactory = tableFactory;
        this.rowKeyDistributor = rowKeyDistributor;
        if (scanCaching > 0) {
            this.scanTaskQueueSize = scanCaching;
        } else {
            this.scanTaskQueueSize = configuration.getInt(
                    HConstants.HBASE_CLIENT_SCANNER_CACHING,
                    HConstants.DEFAULT_HBASE_CLIENT_SCANNER_CACHING);
        }
    }

    public TableName getTableName() {
        return tableName;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Charset getCharset() {
        return charset;
    }

    public TableFactory getTableFactory() {
        return tableFactory;
    }

    public AbstractRowKeyDistributor getRowKeyDistributor() {
        return rowKeyDistributor;
    }

    public int getScanTaskQueueSize() {
        return scanTaskQueueSize;
    }
}
