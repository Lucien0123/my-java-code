package com.lucien.myjavacode;

/**
 * @author huoershuai
 * Created on 2020-08-15
 */
public class main5 {

    public static void main(String[] args) {

        long liveStartTime = 1597459407157L;
        long liveEndTime = 1597459905351L;
    }

    private static long calculateJoinDuration(long liveStartTime, long liveEndTime,
            long liveJoinOrgStartTime, long liveJoinOrgEndTime) {
        long joinLiveDurationMs = 0L;

        if (liveJoinOrgStartTime >= liveEndTime || liveJoinOrgEndTime <= liveStartTime) { //不在对公区间
            return joinLiveDurationMs;
        }
        long tempMills = 0;
        if (liveStartTime > liveJoinOrgStartTime && liveJoinOrgEndTime < liveEndTime) { //右边有交集
            tempMills = liveJoinOrgEndTime - liveStartTime;
        } else if (liveJoinOrgStartTime <= liveStartTime && liveEndTime <= liveJoinOrgEndTime) { //包含
            tempMills = liveEndTime - liveStartTime;
        } else if (liveStartTime < liveJoinOrgStartTime && liveEndTime < liveJoinOrgEndTime) { //左边交集
            tempMills = liveEndTime - liveJoinOrgStartTime;
        } else if (liveStartTime < liveJoinOrgStartTime && liveJoinOrgEndTime < liveEndTime) { //劳模，一直在开播
            tempMills = liveJoinOrgEndTime - liveJoinOrgStartTime;
        }
        joinLiveDurationMs += tempMills;
        return joinLiveDurationMs;
    }
}
