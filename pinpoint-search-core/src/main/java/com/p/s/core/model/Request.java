package com.p.s.core.model;

import lombok.Data;

@Data
public class Request {

    private String transId;

    private String agentId;

    private String appName;

    private String rpcName;

    private int elapsed;

    private int errorCode;

    private String link;

    private String requestTime;

}
