package com.example.hellospringboot.model;

import com.example.hellospringboot.annotation.LeetCode;
import com.example.hellospringboot.annotation.LeetCode.*;

import java.util.*;

@LeetCode(
        level = Level.MEDIUM,
        title = "2080. 区间内查询数字的频率",
        source = "https://leetcode.cn/problems/range-frequency-queries/",
        point = { Point.HASH_MAP, Point.BINARY_SEARCH }
)
public class RangeFreqQuery {

    Map<Integer, List<Integer>> dict = new HashMap<>();

    /**
     * 构造函数，用于初始化数据结构
     *
     * @param arr 输入的整数数组
     */
    public RangeFreqQuery(int[] arr) {
        dict = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            dict.computeIfAbsent(arr[i], (k) -> new ArrayList<>()).add(i);
        }
    }

    /**
     * 查询指定区间内指定值的频率
     *
     * @param left  左边界
     * @param right 右边界
     * @param value 要查询的数值
     * @return 指定区间内指定值的频率
     */
    public int query(int left, int right, int value) {
        List<Integer> freq = dict.get(value);
        if (freq == null){
            return 0;
        }

        return lowBound(freq, right + 1) - lowBound(freq, left);
    }

    /**
     * 二分查找下界
     *
     * @param freq   整数列表
     * @param target 目标值
     * @return 下界的索引
     */
    public int lowBound(List<Integer> freq, int target) {
        int left = -1, right = freq.size();
        while (left + 1!= right) {
            int mid = left + (right - left) / 2;
            if (freq.get(mid) < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

