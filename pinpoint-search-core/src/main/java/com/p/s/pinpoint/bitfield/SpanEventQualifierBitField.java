package com.p.s.pinpoint.bitfield;


import com.p.s.pinpoint.model.SpanEventBo;
import com.p.s.pinpoint.utils.BitFieldUtils;

/**
 * @author Woonduk Kang(emeroad)
 */
public class SpanEventQualifierBitField {

    public static final int SET_ASYNC = 0;

    public static byte buildBitField(SpanEventBo firstSpanEvent) {
        if (firstSpanEvent == null) {
            // no async bit field
            return 0;
        }

        byte bitField = 0;

        final int asyncId = firstSpanEvent.getAsyncId();
        final short asyncSequence = firstSpanEvent.getAsyncSequence();
        if (asyncId == -1 && asyncSequence == -1) {
            bitField = setAsync(bitField, false);
        } else {
            bitField = setAsync(bitField, true);
        }

        return bitField;
    }

    private SpanEventQualifierBitField() {
    }


    public static boolean isSetAsync(byte bitField) {
        return BitFieldUtils.testBit(bitField, SET_ASYNC);
    }

    public static byte setAsync(byte bitField, boolean async) {
        return BitFieldUtils.setBit(bitField, SET_ASYNC, async);
    }


}
