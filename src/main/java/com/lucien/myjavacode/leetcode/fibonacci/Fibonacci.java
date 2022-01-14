package com.lucien.myjavacode.leetcode.fibonacci;

/**
 * @author huoershuai
 * Created on 2022-01-11
 */
public class Fibonacci {

    public static void main(String[] args) {

        System.out.println(fibRecursion(10));
    }

    /**
     * 递归 k^n【最差的性能】
     */
    public static int fibRecursion(int n) {
        if (n < 2) {
            return n;
        }
        return fibRecursion(n - 1) + fibRecursion(n - 2);
    }
}
