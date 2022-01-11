package com.lucien.myjavacode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;

/**
 * @author huoershuai
 * Created on 2020-10-12
 */
public class main8 {


    public static void main(String[] args) {
        int year = 2020;
        int month = 11;
        System.out.println(Arrays.toString(getHiveDtsByMonth(year, month, "yyyyMMdd").toArray()));
    }

    public static List<String> getHiveDtsByMonth(int year, int month, String partitionPattern) {
        List<String> result = new ArrayList<>();
        if (year <= 0 || month <= 0) {
            return result;
        }
        DateTime yearMonth = new DateTime(year, month, 1, 0, 0);
        DateTime start = yearMonth.plusDays(1);  // 9月2日分区
        DateTime end = yearMonth.plusMonths(1);  // 10月1日分区
        while (start.getMillis() <= end.getMillis()) {
            result.add(start.toString(partitionPattern));
            start = start.plusDays(1);
        }
        if (CollectionUtils.isNotEmpty(result)) {
            Collections.sort(result);
        }
        return result;
    }
}
