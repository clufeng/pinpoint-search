package com.p.s.c.model;


/**
 * @author Woonduk Kang(emeroad)
 */
public interface BasicSpan {

    String getAgentId();
    void setAgentId(String agentId);

    String getApplicationId();
    void  setApplicationId(String applicationId);

    long getAgentStartTime();
    void setAgentStartTime(long agentStartTime);

    long getSpanId();
    void setSpanId(long spanId);

    TransactionId getTransactionId();
//    void setTransactionId(TransactionId transactionId);


//    List<SpanEventBo> getSpanEventBoList();
}
