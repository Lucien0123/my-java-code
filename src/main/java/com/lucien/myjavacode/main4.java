package com.lucien.myjavacode;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

/**
 * @author huoershuai
 * Created on 2020-08-15
 */
public class main4 {
    public static void main(String[] args) {

        int BATCH_QUERY_ORG_SIZE = 5;
        int index = 0;
        Integer[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
        List<Integer> orgIdList = Arrays.asList(a);
        int remainder = orgIdList.size() % BATCH_QUERY_ORG_SIZE;
        while (index < orgIdList.size()) {
            List<Integer> temp;
            if (index == 0) {
                temp = orgIdList.subList(index, remainder);
                index = index + remainder;
            } else {
                temp = orgIdList.subList(index, index + BATCH_QUERY_ORG_SIZE);
                index = index + BATCH_QUERY_ORG_SIZE;
            }
            if (CollectionUtils.isNotEmpty(temp)) {
                System.out.println(temp);
            }
        }
    }
}
