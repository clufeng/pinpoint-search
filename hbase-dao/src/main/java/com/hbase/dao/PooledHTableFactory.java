package com.hbase.dao;

import com.hbase.dao.exception.HBaseSystemException;
import com.hbase.dao.parallel.ExecutorFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.security.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * HTableInterfaceFactory based on HTablePool.
 */
public class PooledHTableFactory implements TableFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final int DEFAULT_POOL_SIZE = 256;
    public static final int DEFAULT_WORKER_QUEUE_SIZE = 1024*5;
    public static final boolean DEFAULT_PRESTART_THREAD_POOL = false;

    private final ExecutorService executor;
    private final Connection connection;

    public PooledHTableFactory(Configuration config, User newUser) {
        this(config, newUser, DEFAULT_POOL_SIZE, DEFAULT_WORKER_QUEUE_SIZE, DEFAULT_PRESTART_THREAD_POOL);
    }

    public PooledHTableFactory(Configuration config, User newUser, int poolSize, int workerQueueSize, boolean prestartThreadPool) {
        this.executor = createExecutorService(poolSize, workerQueueSize, prestartThreadPool);

        try {
            this.connection = ConnectionFactory.createConnection(config, executor, newUser);
        } catch (IOException e) {
            throw new HBaseSystemException(e);
        }

    }

    public Connection getConnection() {
        return connection;
    }

    private ExecutorService createExecutorService(int poolSize, int workQueueMaxSize, boolean prestartThreadPool) {

        logger.info("create HConnectionThreadPoolExecutor poolSize:{}, workerQueueMaxSize:{}", poolSize, workQueueMaxSize);

        ThreadPoolExecutor threadPoolExecutor = ExecutorFactory.newFixedThreadPool(poolSize, workQueueMaxSize, "HBase-dao-HConnectionExecutor", true);
        if (prestartThreadPool) {
            logger.info("prestartAllCoreThreads");
            threadPoolExecutor.prestartAllCoreThreads();
        }

        return threadPoolExecutor;
    }


    @Override
    public Table getTable(TableName tableName) {
        try {
            return connection.getTable(tableName, executor);
        } catch (IOException e) {
            throw new HBaseSystemException(e);
        }
    }

    @Override
    public void releaseTable(Table table) {
        if (table == null) {
            return;
        }

        try {
            table.close();
        } catch (IOException ex) {
            throw new HBaseSystemException(ex);
        }
    }


    public void destroy() throws Exception {
        logger.info("PooledHTableFactory.destroy()");

        if (connection != null) {
            try {
                this.connection.close();
            } catch (IOException ex) {
                logger.warn("Connection.close() error:" + ex.getMessage(), ex);
            }
        }

        if (this.executor != null) {
            this.executor.shutdown();
            try {
                final boolean shutdown = executor.awaitTermination(1000 * 5, TimeUnit.MILLISECONDS);
                if (!shutdown) {
                    final List<Runnable> discardTask = this.executor.shutdownNow();
                    logger.warn("discard task size:{}", discardTask.size());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
