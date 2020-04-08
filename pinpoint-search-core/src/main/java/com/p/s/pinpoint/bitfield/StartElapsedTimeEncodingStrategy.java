package com.p.s.pinpoint.bitfield;

/**
 * @author Woonduk Kang(emeroad)
 */
public enum StartElapsedTimeEncodingStrategy {
    // 1 bit
    PREV_EQUALS(0),
    PREV_DELTA(1);

    private final int code;

    StartElapsedTimeEncodingStrategy(int code) {
        this.code = code;
    }
}
