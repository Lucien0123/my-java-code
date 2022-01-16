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

    private static List<List<Integer>> ans = new ArrayList<>();
    private static List<Integer> temp = new ArrayList<>();
    private static boolean[] used;

    public static List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            used[i] = false;
        }
        recur(nums, 0);
        return ans;
    }

    public static void recur(int[] nums, int pos) {
        if (pos == nums.length) {
            // 将temp塞进去结果中
            ans.add(new ArrayList(temp));
            return ;
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println("---");
            if (!used[i]) {
                used[i] = true;   // 要这个数字
                temp.add(nums[i]);
                recur(nums, pos + 1);
                // 还原现场
                used[i] = false;
                temp.remove(temp.size() -1);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> res = new ArrayList<>();
        make(3, 0, res);

        int[] nums = {1,2,3};
        List<List<Integer>> a = permute(nums);
        System.out.println(a);
    }
}
