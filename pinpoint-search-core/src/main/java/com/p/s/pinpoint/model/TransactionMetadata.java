package com.p.s.pinpoint.model;

import java.util.Objects;

/**
 *
 */
public class TransactionMetadata {

    public TransactionMetadata(TransactionId transactionId, long collectorAcceptorTime, long elapsed) {
        this.transactionId = transactionId;
        this.collectorAcceptorTime = collectorAcceptorTime;
        this.elapsed = elapsed;
    }

    public TransactionMetadata() {
    }

    private TransactionId transactionId;

    private long collectorAcceptorTime;

    private long elapsed;

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(TransactionId transactionId) {
        this.transactionId = transactionId;
    }

    public long getCollectorAcceptorTime() {
        return collectorAcceptorTime;
    }

    public void setCollectorAcceptorTime(long collectorAcceptorTime) {
        this.collectorAcceptorTime = collectorAcceptorTime;
    }

    public long getElapsed() {
        return elapsed;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionMetadata that = (TransactionMetadata) o;
        return collectorAcceptorTime == that.collectorAcceptorTime &&
                elapsed == that.elapsed &&
                Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(transactionId, collectorAcceptorTime, elapsed);
    }
}
