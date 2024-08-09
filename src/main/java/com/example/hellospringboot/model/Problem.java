package com.example.hellospringboot.model;


import com.example.hellospringboot.annotation.Easy;
import com.example.hellospringboot.annotation.Medium;
import com.example.hellospringboot.enumerate.Point;

import java.util.*;


public class Problem {
    /**
     * 2009. 使数组连续的最少操作数
     * 思路：滑动窗口
     * <a href="https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-continuous/">...</a>
     */
    @Easy(title = "2009. 使数组连续的最少操作数", source = "https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-continuous/")
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
    @Easy(title = "2529. 正整数和负整数的最大计数", source = "https://leetcode.cn/problems/maximum-count-of-positive-integer-and-negative-integer/")
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


    @Easy(title = "1502. 判断能否形成等差数列", source = "https://leetcode.cn/problems/can-make-arithmetic-progression-from-sequence/description/")
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


    @Easy(title = "3033. 修改矩阵", source = "https://leetcode.cn/problems/modify-the-matrix/")
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
    @Easy(title = "88. 合并两个有序数组", source = "https://leetcode.cn/problems/merge-sorted-array/")
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


    @Medium(
            source = "https://leetcode.cn/problems/shortest-way-to-form-string/",
            title = "1055. 形成字符串的最短路径"
    )
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


    @Easy(
            title = "3131. 找出与数组相加的整数 I",
            source = "https://leetcode.cn/problems/find-the-integer-added-to-array-i/"
    )
    public int addedInteger(int[] nums1, int[] nums2) {
        return min(nums2) - min(nums1);
    }

    public int min(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int m = 1001;
        for (int num : nums) {
            m = Math.min(num, m);
        }
        return m;
    }

    @Medium(point = Point.SLIDE_WINDOW,
            title = "159. 至多包含两个不同字符的最长子串",
            source = "https://leetcode.cn/problems/longest-substring-with-at-most-two-distinct-characters/description")
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int sLength = s.length();
        if (sLength < 3) {
            return sLength;
        }

        int start = 0, end = 0;
        int res = 2;
        HashMap<Character, Integer> map = new HashMap<>();
        while (end < sLength) {

            map.put(s.charAt(end), end);
            end++;

            if (map.size() == 3) {
                int delIndex = Collections.min(map.values());

                map.remove(s.charAt(delIndex));
                start = delIndex + 1;
            }

            res = Math.max(res, end - start);

        }
        return res;
    }

    @Medium(title = "340. 至多包含 K 个不同字符的最长子串",
            point = Point.SLIDE_WINDOW,
            source = "https://leetcode.cn/problems/longest-substring-with-at-most-k-distinct-characters/description")
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int sLength = s.length();
        if (sLength < k + 1) {
            return sLength;
        }

        int start = 0, end = 0;
        int res = k;
        HashMap<Character, Integer> map = new HashMap<>();
        while (end < sLength) {
            map.put(s.charAt(end), end);
            end++;

            if (map.size() == k + 1) {
                int delIndex = Collections.min(map.values());

                map.remove(s.charAt(delIndex));
                start = delIndex + 1;
            }

            res = Math.max(res, end - start);

        }
        return res;
    }


    @Medium(point = Point.SLIDE_WINDOW, title = "c. 最大连续1的个数 II", source = "https://leetcode.cn/problems/max-consecutive-ones-ii/description")
    public int findMaxConsecutiveOnes(int[] nums) {
        int length = nums.length;

        int start = 0, end = 0;
        int zeroFlag = 0;
        int zeroIndex = 0;
        int res = 0;
        while (end < length) {
            if (nums[end] == 0) {
                if (zeroFlag == 1) {
                    zeroFlag = 0;
                    start = zeroIndex + 1;
                }
                zeroIndex = end;
                zeroFlag++;
            }

            end++;
            res = Math.max(res, end - start);
        }

        return res;

    }

    @Medium(title = "3132. 找出与数组相加的整数 II", source = "https://leetcode.cn/problems/find-the-integer-added-to-array-ii/description/")
    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        for (int i = 2; i >= 0; i--) {
            int x = nums2[0] - nums1[i];
            int right = 0;

            for (int j = i; j < nums1.length; j++) {
                if (nums1[j] + x == nums2[right]) {
                    right++;
                    if (nums2.length == right) {
                        return x;
                    }
                }
            }
        }
        return 0;
    }


    @Medium(point = Point.SLIDE_WINDOW,
            title = "1100. 长度为 K 的无重复字符子串",
            source = "https://leetcode.cn/problems/find-k-length-substrings-with-no-repeated-characters")
    public int numKLenSubstrNoRepeats(String s, int k) {
        char[] str = s.toCharArray();
        int length = str.length;

        int start = 0, end = k - 1;

        int res = 0;
        while (end < length) {
            if (!hasDuplicate(str, start, end)) {
                res += 1;
            }
            start++;
            end++;
        }


        return res;
    }


    private boolean hasDuplicate(char[] chars, int start, int end) {
        char[] map = new char[26];

        for (int i = start; i <= end; i++) {
            if (map[chars[i] - 'a'] == 1) {
                return true;
            }
            map[chars[i] - 'a'] += 1;
        }
        return false;
    }
}