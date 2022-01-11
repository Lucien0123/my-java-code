package com.lucien.myjavacode.leetcode.全排列;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huoershuai
 * Created on 2022-01-07
 */
public class CodeFullPermutation {

    public static void make(int n, int level, List<Integer> res) {
        if (n == level) {
            System.out.println(res);
            return;
        }
        for (int i = 1; i < n + 1; i++) {
            if (!res.contains(i)) {
                res.add(i);
                make(n, level + 1, res);
                res.remove(res.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> res = new ArrayList<>();
        make(3, 0, res);
    }
}
