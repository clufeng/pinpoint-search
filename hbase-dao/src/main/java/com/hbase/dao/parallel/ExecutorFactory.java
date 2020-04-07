
package com.hbase.dao.parallel;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public final class ExecutorFactory {

    private static final java.util.concurrent.ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory("HBase-dao-defaultThreadFactory", true);

    private ExecutorFactory() {
    }

    public static ThreadPoolExecutor newFixedThreadPool(int nThreads, int workQueueMaxSize, java.util.concurrent.ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(workQueueMaxSize), threadFactory);
    }

    public static ThreadPoolExecutor newFixedThreadPool(int nThreads, int workQueueMaxSize) {
        return newFixedThreadPool(nThreads, workQueueMaxSize, DEFAULT_THREAD_FACTORY);
    }

    public static ThreadPoolExecutor newFixedThreadPool(int nThreads, int workQueueMaxSize, String threadFactoryName, boolean daemon) {
        java.util.concurrent.ThreadFactory threadFactory = new DefaultThreadFactory(threadFactoryName, daemon);
        return newFixedThreadPool(nThreads, workQueueMaxSize, threadFactory);
    }

}
