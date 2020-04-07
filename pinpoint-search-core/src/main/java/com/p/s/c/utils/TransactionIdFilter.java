package com.p.s.c.utils;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 *
 */
public class TransactionIdFilter {

    private final Set<String> agentIdSetValue;

    private final String rpcNameValue;

    private final boolean hasErrValue;

    private final int thresholdValue;

    private boolean needCheckAgentIdSet;

    private boolean needCheckRpcName;

    private boolean needCheckThreshold;

    public TransactionIdFilter(Set<String> agentIdSet, String rpcName, boolean hasErr, int threshold) {
        this.agentIdSetValue = agentIdSet;
        this.rpcNameValue = rpcName;
        this.hasErrValue = hasErr;
        this.thresholdValue = threshold;

        needCheckAgentIdSet = !CollectionUtils.isEmpty(agentIdSetValue);
        needCheckRpcName = !StringUtils.isEmpty(rpcNameValue);
        needCheckThreshold = thresholdValue > 0;

    }

    public boolean filter(String agentId, String rpc, int errCode, int queryThreshold) {
        return needCheckAgentIdSet && !agentIdSetValue.contains(agentId)
                || needCheckThreshold && queryThreshold < thresholdValue
                || hasErrValue && errCode == 0
                || needCheckRpcName && !StringUtils.isEmpty(rpc) && !rpc.contains(rpcNameValue);
    }


}
