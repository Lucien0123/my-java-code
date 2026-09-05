package com.lucien.myjavacode.leetcode.接雨水;

import java.util.ArrayList;
import java.util.List;

/**
 * 接雨水问题
 * 思路：从左到右依次寻找可以作为桶左边框的元素
 *          ～桶的形式1：右边存在一个比当前桶左边高的元素【最朴素的桶】
 *          ～桶的形式2：右边找不到比当前桶左边高的元素，则以第二高的元素作为桶的右边【如果中间有矮得就能存水，否则是个木桩子】
 *      形成桶以后，以桶的右边为基础，继续找新的桶【注意：前一个桶的右边可以作为新桶的左】
 *      最后，以桶的左右两边Pair对，找中间矮的元素，计算桶的容量，兼容木桩子类型的桶
 * @author huoershuai
 * @date 2026/09/05
 */
public class Solution {

    public int trap(int[] height) {

        List<List<Integer>> bucketList = new ArrayList<>();
        int i = 0;
        while (i < height.length) {
            // 高度为0，不能作为桶的边
            if (i == 0 && height[i] == 0) {
                i++;
                continue;
            }
            int j = i;
            boolean flag = false;
            int secondHeight = 0;
            int secondHeightIndex = 0;
            for (; j< height.length; j++) {
                if (j == i) {
                    continue;
                }
                if (height[j] >= height[i]) {
                    flag = true;
                    break;
                }
                if (height[j] >= secondHeight) {
                    secondHeight = height[j];
                    secondHeightIndex = j;
                    if (j == height.length - 1) {
                        break;
                    }
                }
            }
            // 如果向右找不到比当前高得，就以第二高形成一个桶
            if (secondHeightIndex != i && !flag && secondHeightIndex != 0) {
                List<Integer> bucket = new ArrayList<>();
                bucket.add(i);
                bucket.add(secondHeightIndex);
                bucketList.add(bucket);
                i = secondHeightIndex;
                continue;
            }
            // 如果向右找到了比当前高得，就以当前和找到得第一个高得形成一个桶
            if (j != i && flag) {
                List<Integer> bucket = new ArrayList<>();
                bucket.add(i);
                bucket.add(j);
                bucketList.add(bucket);
                i = j;
                continue;
            }
            i++;
        }
        if (bucketList.isEmpty()) {
            return 0;
        }
        int cap = 0;
        for (List<Integer> bucket : bucketList) {
            cap += calBucketCap(bucket.get(0), bucket.get(1), height);
        }
        return cap;
    }

    /**
     * 计算每个桶的容量
     * 1、以桶的最小边为基础
     * 2、桶包含的每个元素与最小边进行对比
     * 3、桶内的每个元素一定小于两个边
     */
    private int calBucketCap(int left, int right, int[] height) {
        int base = Math.min(height[left], height[right]);
        if (right - left <= 1) {
            return 0;
        }
        int cap = 0;
        for (int i = left + 1; i < right; i++) {
            cap += (base - height[i]);
        }
        return cap;
    }
}
