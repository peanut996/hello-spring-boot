package com.example.hellospringboot.model;


import lombok.Data;

import java.util.*;

@Data
public class Problem {
    /**
     * 2009. 使数组连续的最少操作数
     * 思路：滑动窗口
     * <a href="https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-continuous/">...</a>
     */
    public int minOperations(int[] nums) {
        int len = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        int res = len;
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            int left = list.get(i);
            int right = nums.length - 1 + left;
            while (j < list.size() && list.get(j) <= right) {
                res = Math.min(res, len - (j - i + 1));
                j++;
            }
        }
        return res;
    }

    /**
     * 2529. 正整数和负整数的最大计数
     * 思路：二分搜索
     * <a href="https://leetcode.cn/problems/maximum-count-of-positive-integer-and-negative-integer/">...</a>
     */
    public int maximumCount(int[] nums) {
        int n = nums.length;
        int pos = binarySearch(nums, 1);
        int neg = binarySearch(nums, 0);
        return Math.max(neg, n - pos);
    }

    /**
     * 二分查找需要注意边界问题
     */
    public int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    /**
     * <a href="https://leetcode.cn/problems/can-make-arithmetic-progression-from-sequence/description/"> 1502. 判断能否形成等差数列</a>
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        int length = arr.length;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int n : arr) {
            max = Math.max(max, n);
            min = Math.min(min, n);
        }
        if ((max - min) % (length - 1) != 0) {
            return false;
        }
        int d = (max - min) / (length - 1);
        if (d == 0) {
            return true;
        }

        int[] sorted = new int[length];
        for (int n : arr) {
            int diff = n - min;
            if (diff % d != 0) {
                return false;
            }
            if (sorted[diff / d] != 0) {
                return false;
            }
            sorted[diff / d]++;
        }
        return true;
    }
}