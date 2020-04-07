
package com.hbase.dao.parallel;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class DefaultThreadFactory implements java.util.concurrent.ThreadFactory {

    private final static AtomicInteger FACTORY_NUMBER = new AtomicInteger(0);
    private final AtomicInteger threadNumber = new AtomicInteger(0);

    private final String threadPrefix;
    private final boolean daemon;


    public DefaultThreadFactory() {
        this("HBase-dao", false);
    }

    public DefaultThreadFactory(String threadName) {
        this(threadName, false);
    }

    public DefaultThreadFactory(String threadName, boolean daemon) {
        if (threadName == null) {
            throw new NullPointerException("threadName");
        }
        this.threadPrefix = prefix(threadName, FACTORY_NUMBER.getAndIncrement());
        this.daemon = daemon;
    }

    private String prefix(String threadName, int factoryId) {
        final StringBuilder buffer = new StringBuilder(32);
        buffer.append(threadName);
        buffer.append('(');
        buffer.append(factoryId);
        buffer.append('-');
        return buffer.toString();
    }

    @Override
    public Thread newThread(Runnable job) {
        String newThreadName = createThreadName();
        Thread thread = new Thread(job, newThreadName);
        if (daemon) {
            thread.setDaemon(true);
        }
        return thread;
    }

    private String createThreadName() {
        StringBuilder buffer = new StringBuilder(threadPrefix.length() + 8);
        buffer.append(threadPrefix);
        buffer.append(threadNumber.getAndIncrement());
        buffer.append(')');
        return buffer.toString();
    }

    public static java.util.concurrent.ThreadFactory createThreadFactory(String threadName) {
        return createThreadFactory(threadName, false);
    }

    public static java.util.concurrent.ThreadFactory createThreadFactory(String threadName, boolean daemon) {
        return new DefaultThreadFactory(threadName, daemon);
    }
}
