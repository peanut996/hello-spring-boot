package com.example.hellospringboot.model;


import com.example.hellospringboot.annotation.Easy;
import com.example.hellospringboot.annotation.Medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Problem {
    /**
     * 2009. 使数组连续的最少操作数
     * 思路：滑动窗口
     * <a href="https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-continuous/">...</a>
     */
    @Easy
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
    @Easy
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
    @Easy
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


    /**
     * <a href="https://leetcode.cn/problems/modify-the-matrix/">3033. 修改矩阵</a>
     */
    @Easy
    public int[][] modifiedMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[] maxCol = new int[col];

        for (int i = 0; i < col; i++) {
            for (int[] ints : matrix) {
                maxCol[i] = Math.max(maxCol[i], ints[i]);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = maxCol[j];
                }
            }
        }

        return matrix;
    }

    /**
     * <a href="https://leetcode.cn/problems/merge-sorted-array/">88. 合并两个有序数组</a>
     * 原地倒排序
     */
    @Easy
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int nums1Index = m - 1;
        int nums2Index = n - 1;
        int currentIndex = m + n - 1;

        // 终止条件是 nums1Index 1 < 0 && nums2Index < 0
        for (int i = currentIndex; nums1Index >= 0 || nums2Index >= 0; i--) {
            if (nums1Index == -1) {
                nums1[i] = nums2[nums2Index];
                nums2Index--;
            } else if (nums2Index == -1) {
                nums1[i] = nums1[nums1Index];
                nums1Index--;
            } else if (nums1[nums1Index] > nums2[nums2Index]) {
                nums1[i] = nums1[nums1Index];
                nums1Index--;
            } else {
                nums1[i] = nums2[nums2Index];
                nums2Index--;
            }
        }
    }


    /**
     * <a href="https://leetcode.cn/problems/shortest-way-to-form-string/">1055. 形成字符串的最短路径</a>
     */
    @Medium
    public int shortestWay(String source, String target) {
        int sourceLen = source.length();
        int targetLen = target.length();
        int res = 0;

        int targetIndex = 0;
        int sourceIndex = 0;
        while (targetIndex < targetLen) {
            int cur = targetIndex;

            while (targetIndex < targetLen && sourceIndex < sourceLen) {
                if (source.charAt(sourceIndex) == target.charAt(targetIndex)) {
                    targetIndex++;
                }
                sourceIndex++;
            }

            if (sourceIndex >= sourceLen) {
                sourceIndex = 0;
            }
            if (cur == targetIndex) {
                return -1;
            }

            res++;
        }
        return res;
    }
}