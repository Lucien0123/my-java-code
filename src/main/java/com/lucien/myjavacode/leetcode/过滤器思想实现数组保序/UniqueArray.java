package com.lucien.myjavacode.leetcode.过滤器思想实现数组保序;

import static com.lucien.myjavacode.util.LetCodePrintUtils.printLetCode;

/**
 * 题目：给定一个有序数组，请你 原地 删除重复出现的元素，使每个元素只出现一次，返回删除后数组的长度
 * @author huoershuai
 * Created on 2022-01-11
 */
public class UniqueArray {

    public static void main(String[] args) {
        /*int[] a = {1,2,9,0,0};
        int[] b = {5,8};
        merge1(a,3,  b, 2);*/


    }

    /**
     * 利用过滤器这样的思想，做保序 操作数组
     * · 通过for循环，去考虑每个元素
     *   · 写一个条件，实现Filter，决定什么时候要这个元素
     */
    public static int removeDuplicates(int[] rawArrays) {
        int n = 0;
        for (int i = 0; i < rawArrays.length; i++) {
            if (i == 0 || rawArrays[i] != rawArrays[i - 1]) {
                // 重复就不要，不重复就要
                rawArrays[n] = rawArrays[i];
                n++;
            }
        }
        return n;
    }

    /**
     * 移除所有的 0
     * 依然使用过滤器的思想
     *  · 循环遍历元素
     *      · 设置过滤条件筛选非0元素
     *  · 补0
     */
    public static void moveZero(int[] rawArrays) {
        int n = 0;
        for (int i = 0; i < rawArrays.length; i++) {
            if (rawArrays[i] != 0) {
                // 重复就不要，不重复就要
                rawArrays[n] = rawArrays[i];
                n++;
            }
        }
        while (n < rawArrays.length) {
            rawArrays[n] = 0;
            n++;
        }

    }

    /**
     * 给定两个非递减顺序排列的整数数组，将两个整数数组合并并保持非递减顺序
     * @param a {1,2,3}  非递减
     * @param b {2,5,6}  非递减
     * print: 1,2,2,3,5,6
     */
    public static void merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int n = 0;
        int i = 0;
        int j = 0;
        while (i < a.length || j < b.length) {
            // 为什么要a[i]? 或者j出界了，或者i，j都没出界要小得那个
            if (j >= b.length || (i < a.length && a[i] <= b[j])) {
                result[n] = a[i];
                i++;
            } else {
                result[n] = b[j];
                j++;
            }
            n++;
        }
        System.out.println(printLetCode(result));
    }

    /**
     * 【更省空间】给定两个非递减顺序排列的整数数组，将两个整数数组合并并保持非递减顺序
     * @param a {1,2,3,0,0,0}  非递减
     * @param b {2,5,6}  非递减
     * print: 1,2,2,3,5,6
     */
    public static void merge1(int[] a, int al, int[] b, int bl) {
        int n = 0;
        int i = al - 1;
        int j = bl - 1;
        for (int k = al + bl - 1; k >= 0; k--) {
            // 为什么要a[i]? 或者j出界了，或者i，j都没出界要大得那个
            if (j < 0 || (i >= 0 && a[i] > b[j])) {
                a[k] = a[i];
                i--;
            } else {
                a[k] = b[j];
                j--;
            }
        }
        System.out.println(printLetCode(a));
    }


}
