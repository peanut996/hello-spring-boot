package com.example.hellospringboot.model;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
public class Problem {
    /**
     * 2009. 使数组连续的最少操作数
     * 思路：滑动窗口
     * https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-continuous/
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

}
