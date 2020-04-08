package com.p.s.pinpoint.bitfield;

/**
 * @author Woonduk Kang(emeroad)
 */
public enum ServiceTypeEncodingStrategy {

    // 1 bit
    PREV_EQUALS(0),
    RAW(1);

    private final int code;

    ServiceTypeEncodingStrategy(int code) {
        this.code = code;
    }

}
