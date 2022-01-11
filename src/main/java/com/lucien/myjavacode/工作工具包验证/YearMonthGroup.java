package com.lucien.myjavacode.工作工具包验证;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.alibaba.fastjson.JSON;

/**
 * 年月分组
 * @author huoershuai
 * Created on 2020-07-17
 */
public class YearMonthGroup {

    public static void main(String[] args) {

        DateTime start = new DateTime("2020-07-01").withTimeAtStartOfDay();
        DateTime end = new DateTime("2020-07-01").plusDays(1).minusMillis(1);
        System.out.println(JSON.toJSONString(calDaysGroupByMonth(start, end)));
    }

    public static List<DaysGroupByMonth> calDaysGroupByMonth(DateTime start, DateTime end) {
        List<DaysGroupByMonth> result = new ArrayList<>();
        DateTime tmp = new DateTime(start.getMillis());  // 记录组的开始值
        while (start.getMillis() <= end.getMillis()){
            if (start.getYear() == end.getYear() && start.getMonthOfYear() == end.getMonthOfYear()
                    && start.getDayOfMonth() == end.getDayOfMonth()) {
                result.add(new DaysGroupByMonth(tmp.getYear(), tmp.getMonthOfYear(),
                        tmp.getDayOfMonth(), end.getDayOfMonth()));
                return result;
            }
            start = start.plusDays(1);
            if (start.getYear() > tmp.getYear() || start.getMonthOfYear() > tmp.getMonthOfYear()) {
                result.add(new DaysGroupByMonth(tmp.getYear(), tmp.getMonthOfYear(),
                        tmp.getDayOfMonth(), start.minusDays(1).getDayOfMonth()));
                tmp = new DateTime(start.getMillis());
            }
        }

        return result;
    }

    static class DaysGroupByMonth{
        private int year;
        private int month;
        private int dayStart;
        private int dayEnd;

        public DaysGroupByMonth(int year, int month, int dayStart, int dayEnd) {
            this.year = year;
            this.month = month;
            this.dayStart = dayStart;
            this.dayEnd = dayEnd;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDayStart() {
            return dayStart;
        }

        public void setDayStart(int dayStart) {
            this.dayStart = dayStart;
        }

        public int getDayEnd() {
            return dayEnd;
        }

        public void setDayEnd(int dayEnd) {
            this.dayEnd = dayEnd;
        }
    }
}
