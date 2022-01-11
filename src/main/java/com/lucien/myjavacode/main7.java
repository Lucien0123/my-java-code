package com.lucien.myjavacode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;


/**
 * @author huoershuai
 * Created on 2020-09-23
 */
public class main7 {

    public static void main(String[] args) {
        List<Demo> memberAllRecord = new ArrayList<Demo>() {{
            add(new Demo(1L, 2L, 1));
            add(new Demo(2L, 6L, 3));
            add(new Demo(4L, 5L, 6));
            add(new Demo(5L, 7L, 6));
            add(new Demo(10L, 20L, 2));
            add(new Demo(20L, 30L, 0));
            add(new Demo(30L, Long.MAX_VALUE, 3));
        }};

        Pair<Long, Long> result = getRealCooperateTimeIntersectDateTime(memberAllRecord,
                new DateTime());

        System.out.println(result.getLeft() + "-" + result.getRight());

    }

    private static Pair<Long, Long> getRealCooperateTimeIntersectDateTime(List<Demo> memberAllRecord,
            DateTime dateTime) {
        Pair<Long, Long> result = Pair.of(0L, 0L);
        if (CollectionUtils.isNotEmpty(memberAllRecord)) {
            long workDayStartTime = new DateTime(dateTime).withTimeAtStartOfDay().getMillis();
            long workDayEndTime = new DateTime(dateTime).withTimeAtStartOfDay().plusDays(1).getMillis();
            workDayStartTime = 2L;
            workDayEndTime = 3L;
            memberAllRecord.sort(Comparator.comparingLong(Demo::getStartTime));
            Pair<Long, Long> tmp = null;
            for (Demo record : memberAllRecord) {
                if (tmp == null) {
                    tmp = Pair.of(record.getStartTime(), record.getEndTime());
                } else {
                    if (record.getStartTime() == tmp.getRight()) {
                        tmp = Pair.of(tmp.getLeft(), record.getEndTime());
                    } else {
                        if (tmp.getLeft() < workDayEndTime && tmp.getRight() > workDayStartTime) {
                            return tmp;
                        } else {
                            tmp = Pair.of(record.getStartTime(), record.getEndTime());
                        }
                    }
                }
            }
            if (tmp != null && tmp.getLeft() < workDayEndTime && tmp.getRight() > workDayStartTime) {
                return tmp;
            }
        }
        return result;
    }


    static class Demo {
        long startTime;
        long endTime;
        int age;

        public Demo(long startTime, long endTime, int age) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.age = age;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
