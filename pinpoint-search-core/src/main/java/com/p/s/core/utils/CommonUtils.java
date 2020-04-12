package com.p.s.core.utils;

import com.p.s.core.model.Request;
import com.p.s.pinpoint.model.Application;
import com.p.s.pinpoint.model.Range;
import com.p.s.pinpoint.model.SpanBo;
import com.p.s.pinpoint.utils.ServiceTypeUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.*;

import static com.p.s.pinpoint.utils.TransactionIdUtils.formatString;


public class CommonUtils {

    private static final FastDateFormat df = FastDateFormat.getInstance("yyyy-MM-dd-HH-mm-ss");

    private static final FastDateFormat df2 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss SSS", TimeZone.getDefault(), Locale.getDefault());

    public static final FastDateFormat df3 = FastDateFormat.getInstance("yyyyMMdd");


    public static String buildUrl(String pinpointWebUrl, boolean history, Application app, SpanBo spanBo, Range range) {
        return pinpointWebUrl+"/" + (!history ? "#/" : "") + "transactionList/"
                + app.getName()+ "@" + ServiceTypeUtils.getServiceType(app.getServiceTypeCode()) + "/" + getReadablePeriod(range) + "/"
                + getQueryEndDateTime(range.getFrom()) + "/" + formatString(spanBo.getTransactionId())
                + "-" + spanBo.getCollectorAcceptTime() + "-" + spanBo.getElapsed();
    }

    private static String getReadablePeriod(Range range) {
        return ((range.getTo() - range.getFrom()) / 1000 / 60) + "m";
    }

    private static String getQueryEndDateTime(long from) {
        return df.format(new Date(from));
    }


    public static Request trans(String pinpointHost, boolean history, Application app, SpanBo spanBo, Range range) {
        Request r = trans(spanBo);
        r.setLink(buildUrl(pinpointHost, history, app, spanBo, range));
        return r;
    }

    public static Request trans(SpanBo spanBo) {
        Request r = new Request();
        r.setTransId(formatString(spanBo.getTransactionId()));
        r.setAgentId(spanBo.getAgentId());
        r.setElapsed(spanBo.getElapsed());
        r.setErrorCode(spanBo.getErrCode());
        r.setRpcName(spanBo.getRpc());
        r.setAppName(spanBo.getApplicationId());
        r.setRequestTime(df2.format(spanBo.getStartTime()));
        return r;
    }

    public static Range createRange(int hour) {

        long to = System.currentTimeMillis();

        long from = to - hour * 60 * 60 * 1000; //ms

        return Range.createUncheckedRange(from, to);
    }

    public static Range createRangeByMinute(int minute) {

        long to = System.currentTimeMillis();

        long from = to - minute * 60 * 1000; //ms

        return Range.createUncheckedRange(from, to);
    }



    public static Range createRange(long from, long to, int hour) {

        if(from > 0 && to >= from) {
            return Range.createUncheckedRange(from, to);
        }

        if(hour > 0) {
            return createRange(hour);
        }

        long _to = System.currentTimeMillis();

        long _from = _to - 5 * 60 * 1000; //5m

        return Range.createUncheckedRange(_from, _to);

    }

    public static Range createRangeByMinute(long from, long to, int minute) {

        if(from > 0 && to >= from) {
            return Range.createUncheckedRange(from, to);
        }

        if(minute > 0) {
            return createRangeByMinute(minute);
        }

        long _to = System.currentTimeMillis();

        long _from = _to -  60 * 1000; //1m

        return Range.createUncheckedRange(_from, _to);

    }


    public static <T> List<T> topK(List<T> nums, int k, Comparator<T> comparator) {

        PriorityQueue<T> queue = new PriorityQueue<>(comparator);

        //maintain a heap of size k.
        for (T num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        //get all elements from the heap
        List<T> result = new ArrayList<>();
        while (queue.size() > 0) {
            result.add(queue.poll());
        }

        //reverse the order
        Collections.reverse(result);

        return result;
    }

}
