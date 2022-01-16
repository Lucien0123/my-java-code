package com.lucien.myjavacode.leetcode.other;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

/**
 * 给定一个数n, 如 23121；给定一组数字 A 如 {2,4,9}，求由 A 中元素组成的、小于n的最大数，如小于23121的最大数为 22999
 * @author huoershuai
 * Created on 2022-01-15
 */
public class Question1 {


    public static void main(String[] args) {
        int target = 22000;
        int[] elements = {2, 4, 9};
        calculation(target, elements);
    }

    public static void calculation(int target, int[] elements) {
        String raw = String.valueOf(target);
        int index = elements.length - 1;   // 倒序读取elements
        boolean getMax = false;
        List<Integer> stack = new ArrayList<>();
        for (int i = 0; i < raw.length(); i++) {  // 对原始数字进行循环
            int n = -1;
            int bit = Integer.parseInt(Character.toString(raw.charAt(i)));
            // 从中找到小于等于该位的数字，如果找到了且小于该位，则后续填充数组中的最大值
            for (int j = index; j >= 0; j--) {
                if (getMax || elements[j] <= bit) {
                    if (elements[j] < bit) {
                        getMax = true;
                    }
                    n = elements[j];
                    break;
                }
            }
            if (n >= 0) {  // 如果找到了符合条件的数字，则入栈，继续下一个数字
                stack.add(n);
            } else {
                // 如果没有找到比该位小得数字，且不是第一位
                if (!CollectionUtils.isEmpty(stack)) {
                    int aaa = 0;  // 记录出栈次数
                    // 栈不为空，则出栈，找到比栈顶元素小得数字：
                    //   如果到了终止（并为该次循环补充数组最大值
                    //   如果找不到继续出栈，继续出栈，直到出栈为空
                    while (CollectionUtils.isNotEmpty(stack)) {
                        int temp = stack.remove(stack.size() - 1);
                        int temp1 = -1;
                        for (int j = index; j >= 0; j--) {
                            if (elements[j] < temp) {
                                temp1 = elements[j];
                                break;
                            }
                        }
                        if (temp1 >= 0) {
                            stack.add(temp1);
                            aaa++;
                            break;
                        } else {
                            aaa++;
                        }
                    }
                    // 出n次栈，则补n-1个值，+1表示当前循环也需要填充最大值
                    for (int k = 1; k < aaa + 1; k++) {
                        stack.add(elements[elements.length - 1]);
                    }
                }
                // 发生出栈，要么是位数-1，要么是高位换成了小于原来的数（相当于借位）则后续补充数组最大值
                getMax = true;
            }
        }
        System.out.println(stack);
    }
}
