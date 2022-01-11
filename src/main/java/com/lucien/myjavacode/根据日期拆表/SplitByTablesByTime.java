package com.lucien.myjavacode.根据日期拆表;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import javafx.util.Pair;

/**
 * @author huoershuai
 * Created on 2020-07-30
 */
public class SplitByTablesByTime {

    public static void main(String[] args) {
        long startTime = new DateTime(1593230400000L).withTimeAtStartOfDay().getMillis();
        long endTime = new DateTime(1595822400000L).plusDays(1).withTimeAtStartOfDay().getMillis();
        System.out.println(splitByTables(startTime, endTime));

    }

    private static List<Pair<String, String>> splitByTables(long startTime, long endTime) {
        List<Pair<String, String>> result = new ArrayList<>();
        long tmpStart = startTime;
        long mod =
                (new DateTime(startTime).getDayOfYear() / 10 + 1) % 5;
        while (startTime < endTime) {
            long tmpMod =
                    (new DateTime(startTime).getDayOfYear() / 10 + 1) % 5;
            if (tmpMod != mod || startTime + 24 * 60 * 60 * 1000L == endTime) {
                result.add(new Pair<>(monthDay(tmpStart), monthDay(startTime)));
                tmpStart = startTime;
                mod = tmpMod;
            }
            startTime = startTime + 24 * 60 * 60 * 1000L;
        }
        return result;
    }

    private static String monthDay(long time) {
        DateTime dateTime = new DateTime(time);
        return dateTime.getMonthOfYear() + "-" + dateTime.getDayOfMonth();
    }
}
