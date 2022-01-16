package com.lucien.myjavacode.leetcode.other;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huoershuai
 * Created on 2022-01-15
 */
public class Question2 {

    public static void main(String[] args) {
        int[] nums = {1,1,2,1,1};
        int target = 3;
        System.out.println(numberOfSubarrays(nums, target));
    }

    public static int numberOfSubarrays(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> s = calMap(nums);
        for (Map.Entry<Integer, Integer> e : s.entrySet()) {
            System.out.println(e.getKey() + "-" + e.getValue());
        }
        for (int i = 1; i < nums.length + 1; i++) {
            if (s.getOrDefault(i, 0) - k >= 0) {
                ans += getCount(s.values(), s.getOrDefault(i, 0) - k);
            }
        }
        return ans;
    }

    public static Map<Integer, Integer> calMap(int[] nums) {
        Map<Integer, Integer> ans = new HashMap<>();
        int mapOrder = 1;
        for (int i = 0; i < nums.length; i++) {
            ans.put(mapOrder, ans.getOrDefault(mapOrder - 1, 0) + nums[i] % 2);
            mapOrder++;
        }
        return ans;
    }

    public static int getCount(Collection<Integer> vs, int sub) {
        return (int) vs.stream().filter(e -> e == sub).count() + 1;
    }
}
