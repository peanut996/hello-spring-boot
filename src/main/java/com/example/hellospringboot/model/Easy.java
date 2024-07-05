package com.example.hellospringboot.model;


public class Easy extends Problem {

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


    /**
     * <a href="https://leetcode.cn/problems/modify-the-matrix/">3033. 修改矩阵</a>
     */
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
}