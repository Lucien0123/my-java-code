package com.lucien.myjavacode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;

/**
 * @author huoershuai
 * Created on 2020-07-25
 */
public class Main3 {

    public static void main(String[] args) {
        String queryDate = "2020-07-22";
        Integer beforeDays = 7;
        Pair<Date, Date> result = calculateStartEndDate(queryDate, beforeDays);
        System.out.println(result.getLeft() + "---" + result.getRight());

    }

    public static Pair<Date, Date> calculateStartEndDate(String queryDate, Integer beforeDays) {
        if (beforeDays == null) {
            beforeDays = 7;
        }
        if (beforeDays > 90) {
            beforeDays = 90;
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        DateTime dateTime = new DateTime();
        try {
            if (StringUtils.isNotBlank(queryDate)) {
                //使用SimpleDateFormat的parse()方法生成Date
                Date date = sf.parse(queryDate);
                dateTime = new DateTime(date);
            }
        } catch (ParseException e) {
            return null;
        }
        Date startDate = dateTime.withTimeAtStartOfDay().minusDays(beforeDays).toDate();
        Date endDate = dateTime.withTimeAtStartOfDay().plus(1).toDate();
        return Pair.of(startDate, endDate);
    }
}
