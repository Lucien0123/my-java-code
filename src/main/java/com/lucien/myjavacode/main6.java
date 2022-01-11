package com.lucien.myjavacode;

import org.joda.time.DateTime;

/**
 * @author huoershuai
 * Created on 2020-08-25
 */
public class main6 {

    private static final String TABLE = "cp_cooperate_member_live_record";

    private static final int SUB_TABLE_COUNT = 10;


    public static void main(String[] args1) {
        int year = 2020;
        int month = 9;
        long todayBegin = new DateTime().withTimeAtStartOfDay().getMillis();
        long currentMillis = new DateTime(2020, 9, 21, 9, 15, 0).getMillis();
        DateTime liveStartTime = new DateTime(2020, 9, 19, 12, 0, 0);
        DateTime monthStartDateTime = new DateTime(year, month, 1, 0, 0, 0);
        long monthStartTime = monthStartDateTime.getMillis();  // 查询月份开始结束的毫秒值
        long monthEndTime = monthStartDateTime.plusMonths(1).minusMillis(1).getMillis();
        if (monthEndTime >= currentMillis) {
            monthEndTime = new DateTime().withTimeAtStartOfDay().getMillis();
            if ((currentMillis - todayBegin) < 9 * 60 * 60000 + 30 * 60000
                    && (currentMillis - todayBegin) >= 0) {
                monthEndTime =
                        new DateTime(monthEndTime).minusDays(1).withTimeAtStartOfDay().getMillis();
            }
        }

        System.out.println(calculateIntersectDurationMs(liveStartTime.getMillis(), Long.MAX_VALUE,
                monthStartTime, monthEndTime));
    }

    public static long calculateIntersectDurationMs(long liveStartTime, long liveEndTime,
            long intervalStartTime, long intervalEndTime) {
        long durationMs = 0L;

        if (intervalStartTime >= liveEndTime || intervalEndTime <= liveStartTime) { //不在interval区间
            return durationMs;
        }
        long tempMills = 0;
        if (liveStartTime > intervalStartTime && intervalEndTime < liveEndTime) { //右边有交集
            tempMills = intervalEndTime - liveStartTime;
        } else if (intervalStartTime <= liveStartTime && liveEndTime <= intervalEndTime) { //包含
            tempMills = liveEndTime - liveStartTime;
        } else if (liveStartTime < intervalStartTime && liveEndTime < intervalEndTime) { //左边交集
            tempMills = liveEndTime - intervalStartTime;
        } else if (liveStartTime < intervalStartTime && intervalEndTime < liveEndTime) { //反包含
            tempMills = intervalEndTime - intervalStartTime;
        }
        durationMs += tempMills;
        return durationMs;
    }
}