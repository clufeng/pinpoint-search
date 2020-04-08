package com.p.s.core.model;

import lombok.Data;

import java.util.Set;

/**
 * 请求查询参数
 * @author clufeng
 */
@Data
public class RequestQuery {

    private Set<String> agentIdSet;

    private String rpcName;

    private int threshold;

    private boolean hasErr;

    public RequestQuery() {

    }

    private RequestQuery(Builder builder) {
        this.agentIdSet = builder.agentIdSet;
        this.rpcName = builder.rpcName;
        this.threshold = builder.threshold;
        this.hasErr = builder.hasErr;
    }

    public static class Builder {

        private Set<String> agentIdSet;

        private String rpcName;

        private int threshold;

        private boolean hasErr;

        Builder() {

        }

        public Builder agentIdSet(Set<String> agentIdSet) {
            if(agentIdSet != null && !agentIdSet.isEmpty()) {
                this.agentIdSet = agentIdSet;
            }
            return this;
        }

        public Builder rpcName(String rpcName) {
            if(rpcName != null && rpcName.trim().length() > 0) {
                this.rpcName = rpcName;
            }
            return this;
        }

        public Builder threshold(int threshold) {
            if(threshold > 0) {
                this.threshold = threshold;
            }
            return this;
        }

        public Builder hasErr(int errCode) {
            this.hasErr = errCode != 0;
            return this;
        }

        public RequestQuery build() {
            return new RequestQuery(this);
        }

    }

    public static Builder newBuilder() {
        return new Builder();
    }


}
