package com.example.hellospringboot.model;

import com.example.hellospringboot.annotation.LeetCode;
import com.example.hellospringboot.annotation.LeetCode.Level;
import com.example.hellospringboot.annotation.LeetCode.Point;
import java.util.*;

public class Problem {

    @LeetCode(
        level = Level.EASY,
        title = "2009. 使数组连续的最少操作数",
        source = "https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-continuous/"
    )
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

    @LeetCode(
        level = Level.EASY,
        title = "2529. 正整数和负整数的最大计数",
        source = "https://leetcode.cn/problems/maximum-count-of-positive-integer-and-negative-integer/"
    )
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

    @LeetCode(
        level = Level.EASY,
        title = "1502. 判断能否形成等差数列",
        source = "https://leetcode.cn/problems/can-make-arithmetic-progression-from-sequence/description/"
    )
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

    @LeetCode(
        level = Level.EASY,
        title = "3033. 修改矩阵",
        source = "https://leetcode.cn/problems/modify-the-matrix/"
    )
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

    @LeetCode(
        level = Level.EASY,
        title = "88. 合并两个有序数组",
        source = "https://leetcode.cn/problems/merge-sorted-array/"
    )
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

    @LeetCode(
        level = Level.MEDIUM,
        title = "1055. 形成字符串的最短路径",
        source = "https://leetcode.cn/problems/shortest-way-to-form-string/"
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

    @LeetCode(
        level = Level.EASY,
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

    @LeetCode(
        level = Level.MEDIUM,
        title = "159. 至多包含两个不同字符的最长子串",
        source = "https://leetcode.cn/problems/longest-substring-with-at-most-two-distinct-characters/description",
        point = Point.SLIDE_WINDOW
    )
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

    @LeetCode(
        level = Level.MEDIUM,
        title = "340. 至多包含 K 个不同字符的最长子串",
        source = "https://leetcode.cn/problems/longest-substring-with-at-most-k-distinct-characters/description",
        point = Point.SLIDE_WINDOW
    )
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

    @LeetCode(
        level = Level.MEDIUM,
        title = "c. 最大连续1的个数 II",
        source = "https://leetcode.cn/problems/max-consecutive-ones-ii/description",
        point = Point.SLIDE_WINDOW
    )
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

    @LeetCode(
        level = Level.MEDIUM,
        title = "3132. 找出与数组相加的整数 II",
        source = "https://leetcode.cn/problems/find-the-integer-added-to-array-ii/description/"
    )
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

    @LeetCode(
        level = Level.MEDIUM,
        title = "1100. 长度为 K 的无重复字符子串",
        source = "https://leetcode.cn/problems/find-k-length-substrings-with-no-repeated-characters",
        point = Point.SLIDE_WINDOW
    )
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

    @LeetCode(
        level = Level.EASY,
        title = "760. 找出变位映射",
        source = "https://leetcode.cn/problems/find-anagram-mappings/description",
        point = Point.HASH
    )
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }

        for (int i = 0; i < nums1.length; i++) {
            int val = nums1[i];
            nums1[i] = map.get(val);
        }

        return nums1;
    }

    @LeetCode(
        level = Level.EASY,
        title = "266. 回文排列",
        source = "https://leetcode.cn/problems/palindrome-permutation/description",
        point = Point.HASH
    )
    public boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            int count = map.getOrDefault(c, 0);
            count++;
            map.put(c, count);
        }

        int odd = 0;
        for (int num : map.values()) {
            if ((num % 2) != 0) {
                odd++;
                if (odd > 1) {
                    return false;
                }
            }
        }

        return true;
    }

    @LeetCode(
        level = Level.EASY,
        title = "734. 句子相似性",
        source = "https://leetcode.cn/problems/sentence-similarity/description",
        point = Point.HASH
    )
    public boolean areSentencesSimilar(
        String[] sentence1,
        String[] sentence2,
        List<List<String>> similarPairs
    ) {
        if (sentence1.length != sentence2.length) {
            return false;
        }

        HashMap<String, Set<String>> dict = new HashMap<>();

        for (List<String> pair : similarPairs) {
            String word1 = pair.get(0);
            String word2 = pair.get(1);

            dict.putIfAbsent(word1, new HashSet<String>());
            dict.get(word1).add(word2);

            dict.putIfAbsent(word2, new HashSet<String>());
            dict.get(word2).add(word1);
        }

        for (int i = 0; i < sentence1.length; i++) {
            boolean isSame =
                sentence1[i].equals(sentence2[i]) ||
                (dict.containsKey(sentence1[i]) &&
                    dict.get(sentence1[i]).contains(sentence2[i]));

            if (!isSame) {
                return false;
            }
        }

        return true;
    }

    @LeetCode(
        level = Level.EASY,
        title = "1165. 单行键盘",
        source = "https://leetcode.cn/problems/single-row-keyboard/description",
        point = Point.HASH
    )
    public int calculateTime(String keyboard, String word) {
        HashMap<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            dict.put(keyboard.charAt(i), i);
        }

        int res = 0;
        char last = keyboard.charAt(0);
        for (char c : word.toCharArray()) {
            res += Math.abs(dict.get(c) - dict.get(last));
            last = c;
        }
        return res;
    }

    @LeetCode(
        level = Level.EASY,
        title = "1133. 最大唯一数",
        source = "https://leetcode.cn/problems/largest-unique-number/description",
        point = Point.HASH
    )
    public int largestUniqueNumber(int[] nums) {
        HashMap<Integer, Integer> dict = new HashMap<>();

        for (int n : nums) {
            dict.put(n, dict.getOrDefault(n, 0) + 1);
        }

        int max = -1;

        for (int n : nums) {
            if (dict.get(n) == 1) {
                max = Math.max(max, n);
            }
        }

        return max;
    }

    @LeetCode(
        level = Level.EASY,
        title = "1426. 数元素",
        source = "https://leetcode.cn/problems/counting-elements/description",
        point = Point.HASH
    )
    public int countElements(int[] arr) {
        HashMap<Integer, Integer> dict = new HashMap<>();

        for (int n : arr) {
            dict.put(n, dict.getOrDefault(n, 0) + 1);
        }

        int res = 0;
        for (int n : arr) {
            int t = dict.getOrDefault(n + 1, 0);
            if (t >= 1) {
                res += 1;
            }
        }

        return res;
    }

    @LeetCode(
        level = Level.EASY,
        title = "3151. 特殊数组 I",
        source = "https://leetcode.cn/problems/special-array-i/"
    )
    public boolean isArraySpecial(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                return false;
            }
        }

        return true;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "249. 移位字符串分组",
        source = "https://leetcode.cn/problems/group-shifted-strings/description",
        point = Point.HASH
    )
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0) {
            return new ArrayList<>();
        }

        String dash = "-";
        HashMap<String, List<String>> dict = new HashMap<>();
        for (String s : strings) {
            StringBuilder tag = new StringBuilder();
            char first = s.charAt(0);
            for (char c : s.toCharArray()) {
                tag.append(dash);
                tag.append((c - first + 26) % 26);
            }

            String flag = tag.toString();
            if (!dict.containsKey(flag)) {
                dict.put(flag, new ArrayList<>());
            }
            dict.get(flag).add(s);
        }

        return new ArrayList<>(dict.values());
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "1198. 找出所有行中最小公共元素",
        source = "https://leetcode.cn/problems/find-smallest-common-element-in-all-rows/description",
        point = { Point.HASH, Point.BIN_SEARCH }
    )
    public int smallestCommonElement(int[][] mat) {
        //        int count[] = new int[10001];
        //        int n = mat.length, m = mat[0].length;
        //        for (int j = 0; j < m; ++j) {
        //            for (int i = 0; i < n; ++i) {
        //                if (++count[mat[i][j]] == n) {
        //                    return mat[i][j];
        //                }
        //            }
        //        }
        //        return -1;

        int n = mat.length, m = mat[0].length;
        for (int j = 0; j < m; ++j) {
            boolean found = true;
            for (int i = 1; i < n && found; ++i) {
                found = Arrays.binarySearch(mat[i], mat[0][j]) >= 0;
            }
            if (found) {
                return mat[0][j];
            }
        }
        return -1;
    }

    @LeetCode(
        level = Level.EASY,
        title = "422. 有效的单词方块",
        source = "https://leetcode.cn/problems/valid-word-square/description",
        point = Point.MATRIX
    )
    public boolean validWordSquare(List<String> words) {
        int size = words.size();

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < words.get(i).length(); ++j) {
                if (
                    j >= size ||
                    i >= words.get(j).length() ||
                    words.get(i).charAt(j) != words.get(j).charAt(i)
                ) {
                    return false;
                }
            }
        }

        return true;
    }

    @LeetCode(
        level = Level.MEDIUM,
        point = Point.PREFIX,
        title = "3152. 特殊数组 II",
        source = "https://leetcode.cn/problems/special-array-ii/description/"
    )
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int[] prefix = new int[nums.length];

        for (int i = 1; i < nums.length; ++i) {
            prefix[i] =
                prefix[i - 1] + (nums[i] % 2 == nums[i - 1] % 2 ? 1 : 0);
        }
        boolean[] res = new boolean[queries.length];

        for (int i = 0; i < queries.length; ++i) {
            res[i] = prefix[queries[i][0]] == prefix[queries[i][1]];
        }

        return res;
    }

    /**
     * 根据题意可以知，对于每个索引 i 的最长特殊数组的长度 dp[i] 计算方法如下：
     * <p>
     * 如果 nums[i] 与左边相邻的元素 nums[i−1] 奇偶性相同，则此时 dp[i]=1; 如果 nums[i] 与左边相邻的元素 nums[i−1] 奇偶性不同，则此时 nums[i] 可以追加到以 nums[i−1]
     * 为结尾的最长特殊数组的后面，则 dp[i]=dp[i−1]+1; 在判断两个元素奇偶性是否相同时，可以利用位运算来实现，对于给定的元素 a,b，当满足 (a⊕b)&1=1 时，则 a,b 的奇偶性不同，否则奇偶性相同；
     */
    @LeetCode(
        level = Level.MEDIUM,
        point = Point.DP,
        title = "3152. 特殊数组 II",
        source = "https://leetcode.cn/problems/special-array-ii/description/"
    )
    public boolean[] isArraySpecial_DP(int[] nums, int[][] queries) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] % 2 != nums[i - 1] % 2) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
        }

        boolean[] res = new boolean[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int y = queries[i][1], x = queries[i][0];
            res[i] = dp[y] >= (y - x + 1);
        }
        return res;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "531. 孤独像素 I",
        source = "https://leetcode.cn/problems/lonely-pixel-i/description"
    )
    public int findLonelyPixel(char[][] picture) {
        char black = 'B';

        int m = picture.length;
        int n = picture[0].length;

        // 预处理
        int[] row = new int[m];
        int[] col = new int[n];

        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == black) {
                    row[i]++;
                    col[j]++;
                }
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == black) {
                    if (row[i] == 1 && col[j] == 1) {
                        res += 1;
                    }
                }
            }
        }

        return res;
    }

    @LeetCode(
        level = Level.MEDIUM,
        source = "https://leetcode.cn/problems/compare-strings-by-frequency-of-the-smallest-character/",
        title = "1170. 比较字符串最小字母出现频次",
        point = Point.BIN_SEARCH
    )
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] queriesF = new int[queries.length];
        int[] wordsF = new int[words.length];
        for (int i = 0; i < queries.length; i++) {
            queriesF[i] = f(queries[i]);
        }
        for (int i = 0; i < words.length; i++) {
            wordsF[i] = f(words[i]);
        }
        Arrays.sort(wordsF);
        for (int i = 0; i < queries.length; i++) {
            queriesF[i] = wordsF.length - bin(wordsF, queriesF[i]);
        }
        return queriesF;
    }

    public int bin(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 != right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public int f(String s) {
        int[] dict = new int[26];
        for (char c : s.toCharArray()) {
            dict[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (dict[i] > 0) {
                return dict[i];
            }
        }
        return 0;
    }

    /**
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
    @LeetCode(
        level = Level.MEDIUM,
        point = Point.HASH,
        source = "https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked",
        title = "128. 最长连续序列"
    )
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longest = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int cur = 1;
                int curNum = num;
                while (set.contains(curNum + 1)) {
                    cur++;
                    curNum++;
                }

                longest = Math.max(longest, cur);
            }
        }
        return longest;
    }

    @LeetCode(
        level = Level.EASY,
        point = Point.TWO_POINTER,
        title = "283. 移动零",
        source = "https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=top-100-liked"
    )
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                if (slow != fast) {
                    nums[fast] = 0;
                }
                slow++;
            }
            fast++;
        }
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "11. 盛最多水的容器",
        point = Point.TWO_POINTER,
        source = "https://leetcode.cn/problems/container-with-most-water/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            int area;
            if (height[left] < height[right]) {
                area = height[left] * (right - left);
                left++;
            } else {
                area = height[right] * (right - left);
                right--;
            }
            max = Math.max(max, area);
        }
        return max;
    }

    /**
     * 给定一个整数数组 nums，找出所有和为 0 的三元组，且不重复。
     *
     * @param nums 整数数组
     * @return 所有和为 0 的三元组列表
     */
    @LeetCode(
        level = Level.MEDIUM,
        title = "15. 三数之和",
        point = Point.TWO_POINTER,
        source = "https://leetcode.cn/problems/3sum/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<List<Integer>> threeSum(int[] nums) {
        // 对数组进行排序
        Arrays.sort(nums);
        // 用于存储结果的列表
        List<List<Integer>> res = new ArrayList<>();

        // 遍历数组，i 作为第一个数的索引
        for (int i = 0; i < nums.length - 2; i++) {
            // 如果当前数与前一个数相同，则跳过，避免重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 左指针初始化为 i + 1，右指针初始化为数组末尾
            int left = i + 1, right = nums.length - 1;
            // 当左指针小于右指针时，进行循环
            while (left < right) {
                // 计算当前三个数的和
                int sum = nums[i] + nums[left] + nums[right];
                // 如果和为 0，则将这三个数添加到结果列表中
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 跳过重复的数
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 跳过重复的数
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 移动指针，继续寻找下一个可能的解
                    left++;
                    right--;
                    // 如果和小于 0，则左指针右移，增大和
                } else if (sum < 0) {
                    left++;
                    // 如果和大于 0，则右指针左移，减小和
                } else {
                    right--;
                }
            }
        }
        // 返回结果列表
        return res;
    }

    /**
     * 计算给定高度数组中可以捕获的雨水量
     *
     * @param height 整数数组，表示每个位置的高度
     * @return 可以捕获的雨水量
     */
    @LeetCode(
        level = Level.HARD,
        title = "42. 接雨水",
        point = Point.TWO_POINTER,
        source = "https://leetcode.cn/problems/trapping-rain-water"
    )
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int maxLeft = 0, maxRight = 0;
        int totalWater = 0;
        // 从两端向中间遍历数组
        while (left < right) {
            // 如果左边的高度小于右边的高度
            if (height[left] < height[right]) {
                // 更新左边的当前高度
                int currentLeft = height[left];
                // 如果当前高度大于左边的最大高度，则更新最大高度
                if (currentLeft > maxLeft) {
                    maxLeft = currentLeft;
                } else {
                    // 否则，计算并累加当前位置可以捕获的雨水量
                    totalWater += maxLeft - currentLeft;
                }
                // 左指针向右移动
                left++;
            }
            // 如果右边的高度小于等于左边的高度
            else {
                // 更新右边的当前高度
                int currentRight = height[right];
                // 如果当前高度大于右边的最大高度，则更新最大高度
                if (currentRight > maxRight) {
                    maxRight = currentRight;
                } else {
                    // 否则，计算并累加当前位置可以捕获的雨水量
                    totalWater += maxRight - currentRight;
                }
                // 右指针向左移动
                right--;
            }
        }
        // 返回总雨水量
        return totalWater;
    }

    /**
     * 计算给定字符串中不重复字符的最长子串长度
     *
     * @param s 输入的字符串
     * @return 最长子串的长度
     */
    @LeetCode(
        level = Level.MEDIUM,
        title = "3. 无重复字符的最长子串",
        point = { Point.SLIDE_WINDOW, Point.TWO_POINTER },
        source = "https://leetcode.cn/problems/longest-substring-without-repeating-characters/"
    )
    public int lengthOfLongestSubstring(String s) {
        // 将字符串转换为字符数组，以便于处理
        char[] chars = s.toCharArray();

        // 使用哈希集合来存储当前窗口中的字符，确保它们都是唯一的
        Set<Character> charCache = new HashSet<>();

        // 初始化最大长度为 0
        int maxLength = 0;
        // 初始化窗口的左边界
        int left = 0;

        // 遍历字符数组，right 指针表示窗口的右边界
        for (int right = 0; right < chars.length; right++) {
            // 获取当前字符
            char c = chars[right];
            // 如果字符已经在窗口中，需要移动左边界，直到该字符被移除
            while (charCache.contains(c)) {
                // 从窗口中移除最左边的字符
                charCache.remove(chars[left++]);
            }
            // 将当前字符添加到窗口中
            charCache.add(c);
            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        // 返回最长子串的长度
        return maxLength;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "438. 找到字符串中所有字母异位词",
        point = Point.SLIDE_WINDOW,
        source = "https://leetcode.cn/problems/find-all-anagrams-in-a-string"
    )
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] cache = new int[26];
        for (char c : p.toCharArray()) {
            cache[c - 'a']++;
        }

        int left = 0;
        char[] chars = s.toCharArray();

        for (int right = 0; right < chars.length; right++) {
            int c = chars[right];
            cache[c - 'a']--;
            while (left < chars.length && hasCacheSmallerThanZero(cache)) {
                char leftChar = chars[left];
                cache[leftChar - 'a']++;
                left++;
            }

            if (
                right - left + 1 == p.length() &&
                !hasCacheSmallerThanZero(cache)
            ) {
                res.add(left);
            }
        }

        return res;
    }

    boolean hasCacheSmallerThanZero(int[] cache) {
        for (int n : cache) {
            if (n < 0) {
                return true;
            }
        }
        return false;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "560. 和为 K 的子数组",
        point = Point.PREFIX,
        source = "https://leetcode.cn/problems/subarray-sum-equals-k/"
    )
    public int subarraySum(int[] nums, int k) {
        int prefix = 0;
        int res = 0;
        // 存储前缀和及其出现次数的哈希表
        Map<Integer, Integer> preSumCount = new HashMap<>();
        // 初始化前缀和为 0 的情况，出现次数为 1
        preSumCount.put(0, 1);
        for (int n : nums) {
            // 计算当前前缀和
            prefix += n;
            // 计算目标前缀和
            int target = prefix - k;
            // 若哈希表中存在目标前缀和，将其出现次数累加到结果中
            res += preSumCount.getOrDefault(target, 0);
            // 更新前缀和的出现次数
            preSumCount.merge(prefix, 1, Integer::sum);
        }
        return res;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "56. 合并区间",
        source = "https://leetcode.cn/problems/merge-intervals",
        point = { Point.ARRAY }
    )
    public int[][] merge(int[][] intervals) {
        Arrays.sort(
            intervals,
            Comparator.comparingInt(interval -> interval[0])
        );

        List<int[]> res = new ArrayList<>();

        for (int[] interval : intervals) {
            if (res.isEmpty()) {
                res.add(interval);
                continue;
            }
            int[] last = res.get(res.size() - 1);
            if (interval[0] > last[1]) {
                res.add(interval);
            } else {
                last[1] = Math.max(interval[1], last[1]);
            }
        }
        return res.toArray(new int[0][0]);
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "189. 轮转数组",
        source = "https://leetcode.cn/problems/rotate-array/",
        point = { Point.ARRAY }
    )
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % nums.length;
        reverse(nums, len - k, len - 1);
        reverse(nums, 0, len - k - 1);
        reverse(nums, 0, len - 1);
    }

    void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "238. 除自身以外数组的乘积",
        source = "https://leetcode.cn/problems/product-of-array-except-self/",
        point = { Point.ARRAY }
    )
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] leftPrefix = new int[len];
        leftPrefix[0] = 1;

        int[] rightPrefix = new int[len];
        rightPrefix[len - 1] = 1;
        for (int i = 1; i < len; i++) {
            leftPrefix[i] = leftPrefix[i - 1] * nums[i - 1];
        }
        for (int i = len - 2; i > -1; i--) {
            rightPrefix[i] = rightPrefix[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < len; i++) {
            nums[i] = leftPrefix[i] * rightPrefix[i];
        }
        return nums;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "73. 矩阵置零",
        source = "https://leetcode.cn/problems/set-matrix-zeroes",
        point = { Point.ARRAY }
    )
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Check if the first row and column have zeros
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Mark zeros in the first row and column
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Set zeros based on the marks
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Set zeros for the first row and column if necessary

        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "54. 螺旋矩阵",
        point = { Point.ARRAY },
        source = "https://leetcode.cn/problems/spiral-matrix/"
    )
    public List<Integer> spiralOrder(int[][] matrix) {
        final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int row = 0, col = 0;
        int directionIndex = 0;
        for (int i = 0; i < m * n; i++) {
            visited[row][col] = true;
            res.add(matrix[row][col]);
            int nextRow = row + DIRECTIONS[directionIndex][0];
            int nextCol = col + DIRECTIONS[directionIndex][1];

            if (
                nextRow < 0 ||
                nextCol < 0 ||
                nextRow > m - 1 ||
                nextCol > n - 1 ||
                visited[nextRow][nextCol]
            ) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row = row + DIRECTIONS[directionIndex][0];
            col = col + DIRECTIONS[directionIndex][1];
        }
        return res;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "48. 旋转图像",
        point = { Point.ARRAY },
        source = "https://leetcode.cn/problems/rotate-image"
    )
    public void rotate(int[][] matrix) {
        int len = matrix.length;

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][len - 1 - j];
                matrix[i][len - 1 - j] = tmp;
            }
        }
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "240. 搜索二维矩阵 II",
        source = "https://leetcode.cn/problems/search-a-2d-matrix-ii/",
        point = { Point.ARRAY }
    )
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix[0] == null) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0;
        int col = n - 1;
        while (row < m && col > -1) {
            int cur = matrix[row][col];
            if (cur == target) {
                return true;
            } else if (cur > target) {
                col -= 1;
            } else if (cur < target) {
                row += 1;
            }
        }
        return false;
    }

    @LeetCode(
        level = Level.EASY,
        title = "160. 相交链表",
        source = "https://leetcode.cn/problems/intersection-of-two-linked-lists/",
        point = { Point.TWO_POINTERS, Point.LINKED_LIST }
    )
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            if (a == null) {
                a = headB;
            } else {
                a = a.next;
            }
            if (b == null) {
                b = headA;
            } else {
                b = b.next;
            }
        }
        return a;
    }

    @LeetCode(
        level = Level.EASY,
        title = "206. 反转链表",
        source = "https://leetcode.cn/problems/reverse-linked-list",
        point = { Point.LINKED_LIST }
    )
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    @LeetCode(
        level = Level.EASY,
        title = "234. 回文链表",
        source = "https://leetcode.cn/problems/palindrome-linked-list",
        point = { Point.LINKED_LIST }
    )
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode reversedHalf = reverseList(slow);

        ListNode p1 = head, p2 = reversedHalf;

        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }

    // 递归
    // ListNode isPalindrome_left;

    // public boolean isPalindrome(ListNode head) {
    //     isPalindrome_left = head;
    //     return isPalindromeHelper(head);
    // }

    // boolean isPalindromeHelper(ListNode right) {
    //     if (right == null) return true;
    //     boolean res = isPalindromeHelper(right.next);
    //     if (!res) return false;
    //     if (isPalindrome_left.val != right.val) {
    //         return false;
    //     }
    //     isPalindrome_left = isPalindrome_left.next;
    //     return true;
    // }

    @LeetCode(
        level = LeetCode.Level.EASY,
        title = "141. 环形链表",
        source = "https://leetcode.cn/problems/linked-list-cycle/",
        point = { Point.LINKED_LIST, Point.TWO_POINTERS }
    )
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "142. 环形链表 II",
        source = "https://leetcode.cn/problems/linked-list-cycle-ii/",
        point = { Point.LINKED_LIST, Point.TWO_POINTERS }
    )
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode f = head;
                while (f != null && f.next != null) {
                    if (slow == f) {
                        return slow;
                    }
                    slow = slow.next;
                    f = f.next;
                }
            }
        }
        return null;
    }

    @LeetCode(
        level = LeetCode.Level.EASY,
        title = "21. 合并两个有序链表",
        source = "https://leetcode.cn/problems/merge-two-sorted-lists/",
        point = { Point.LINKED_LIST }
    )
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 创建虚拟头节点
        ListNode dummy = new ListNode();
        ListNode current = dummy; // 使用 current 指针进行链表拼接

        // 循环比较 list1 和 list2 的节点值
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next; // current 指针后移
        }

        // 处理剩余节点
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return dummy.next;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "2. 两数相加",
        source = "https://leetcode.cn/problems/add-two-numbers/",
        point = { Point.LINKED_LIST }
    )
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }
        return dummy.next;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "19. 删除链表的倒数第 N 个结点",
        source = "https://leetcode.cn/problems/remove-nth-node-from-end-of-list/",
        point = { Point.LINKED_LIST, Point.TWO_POINTERS }
    )
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = head;
        ListNode slow = dummy;

        while (n-- > 0) {
            if (fast == null) {
                return head;
            }
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "24. 两两交换链表中的节点",
        source = "https://leetcode.cn/problems/swap-nodes-in-pairs/",
        point = { Point.LINKED_LIST }
    )
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode current = head;

        while (current != null && current.next != null) {
            ListNode first = current;
            ListNode second = current.next;

            // 交换节点
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // 更新指针
            prev = first;
            current = first.next;
        }
        return dummy.next;
    }

    @LeetCode(
        level = Level.HARD,
        title = "25. K 个一组翻转链表",
        source = "https://leetcode.cn/problems/reverse-nodes-in-k-group/",
        point = { Point.LINKED_LIST }
    )
    public ListNode reverseKGroup(ListNode head, int k) {
        // 如果链表为空或者 k 小于等于 1，则不需要翻转，直接返回
        if (head == null || k <= 1) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode start = head;

        while (true) {
            ListNode end = start;
            for (int i = 1; i < k; i++) {
                if (end == null || end.next == null) {
                    // 如果剩余节点不足 k 个，则不需要翻转，直接返回
                    return dummy.next;
                }
                end = end.next;
            }
            ListNode nextGroupStart = end.next;

            // 将当前 k 组的尾节点 next 指针置空，方便反转
            end.next = null;

            reverseList(start);

            // 翻转后重新连接
            pre.next = end;
            start.next = nextGroupStart;

            // 更新指针
            pre = start;
            start = nextGroupStart;
        }
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "138. 复制带随机指针的链表",
        source = "https://leetcode.cn/problems/copy-list-with-random-pointer/",
        point = { Point.LINKED_LIST, Point.HASH }
    )
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node curr = head;

        // 1. 创建复制节点并插入到原节点之后
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        curr = head;
        // 2. 设置复制节点的 random 指针
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        curr = head;
        Node dummy = new Node(0);
        Node copyCurr = dummy;
        // 3. 分离原链表和复制链表
        while (curr != null) {
            Node copy = curr.next;
            copyCurr.next = copy;
            curr.next = copy.next;
            copyCurr = copyCurr.next;
            curr = curr.next;
        }

        return dummy.next;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "148. 排序链表",
        source = "https://leetcode.cn/problems/sort-list/",
        point = { Point.LINKED_LIST, Point.SORT, Point.MERGE_SORT }
    )
    public ListNode sortList(ListNode head) {
        // 递归终止条件：空链表或只有一个节点的链表
        if (head == null || head.next == null) {
            return head;
        }

        // 快慢指针找到链表中点
        ListNode slow = head;
        ListNode fast = head.next; // fast 从 head.next 开始，确保 mid 偏左
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 分割链表
        ListNode mid = slow;
        ListNode midNext = mid.next;
        mid.next = null; // 断开前半部分链表

        // 递归排序左右两部分
        ListNode left = sortList(head);
        ListNode right = sortList(midNext);

        // 合并已排序的两个链表
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        while (left != null || right != null) { // 循环直到两个链表都为空
            // 这里不需要每次都新建节点，直接接上原链表节点即可, 优化空间复杂度
            if (left != null && right != null) {
                if (left.val < right.val) {
                    current.next = left;
                    left = left.next;
                } else {
                    current.next = right;
                    right = right.next;
                }
            } else if (left != null) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

        return dummy.next;
    }

    @LeetCode(
        level = Level.HARD,
        title = "23. 合并 K 个升序链表",
        source = "https://leetcode.cn/problems/merge-k-sorted-lists/",
        point = {
            Point.LINKED_LIST,
            Point.MERGE_SORT,
            Point.HEAP,
            Point.DIVIDE_AND_CONQUER,
        }
    )
    public ListNode mergeKLists(ListNode[] lists) {
        // 边界条件处理：如果 lists 为 null 或空数组，直接返回 null
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 使用分治法合并链表
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }

    ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        // 递归终止条件：如果 start > end，说明没有链表需要合并，返回 null
        if (start > end) {
            return null;
        }
        // 递归终止条件：如果 start == end，说明只有一个链表，直接返回该链表
        if (start == end) {
            return lists[start];
        }

        // 计算中间索引，避免 (start + end) 可能导致的整数溢出
        int mid = start + (end - start) / 2;
        // 递归合并左半部分链表
        ListNode first = mergeKListsHelper(lists, start, mid);
        // 递归合并右半部分链表，注意起始索引是 mid + 1
        ListNode second = mergeKListsHelper(lists, mid + 1, end);
        // 合并左右两部分链表
        return merge(first, second);
    }

    ListNode merge(ListNode first, ListNode second) {
        // 创建哑节点作为合并后链表的头节点
        ListNode dummy = new ListNode();
        // current 指针用于构建合并后的链表
        ListNode current = dummy;
        // 循环比较两个链表的节点值，将较小的节点添加到合并后的链表中
        while (first != null && second != null) {
            if (first.val < second.val) {
                current.next = first;
                first = first.next;
            } else {
                current.next = second;
                second = second.next;
            }
            current = current.next;
        }
        // 将剩余的节点添加到合并后的链表中
        current.next = first == null ? second : first;
        // 返回合并后的链表头节点（哑节点的下一个节点）
        return dummy.next;
    }

    @LeetCode(
        level = Level.EASY,
        title = "94. 二叉树的中序遍历",
        source = "https://leetcode.cn/problems/binary-tree-inorder-traversal/",
        point = {
            Point.BINARY_TREE,
            Point.RECURSION,
            Point.TRAVERSAL,
            Point.MORRIS_TRAVERSAL,
        }
    )
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    @LeetCode(
        level = Level.EASY,
        title = "104. 二叉树的最大深度",
        source = "https://leetcode.cn/problems/maximum-depth-of-binary-tree/",
        point = { Point.BINARY_TREE, Point.RECURSION, Point.DFS, Point.BFS }
    )
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    @LeetCode(
        level = Level.EASY,
        title = "226. 翻转二叉树",
        source = "https://leetcode.cn/problems/invert-binary-tree/",
        point = { Point.BINARY_TREE, Point.RECURSION }
    )
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 递归地翻转左右子树，并直接交换
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);

        root.left = left;
        root.right = right;

        return root;
    }

    @LeetCode(
        level = Level.EASY,
        title = "101. 对称二叉树",
        source = "https://leetcode.cn/problems/symmetric-tree/",
        point = { Point.BINARY_TREE, Point.RECURSION }
    )
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetricHelper(root.left, root.right);
    }

    boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }

        return (
            left.val == right.val &&
            isSymmetricHelper(left.right, right.left) &&
            isSymmetricHelper(left.left, right.right)
        );
    }

    int diameterOfBinaryTreeMaxDiameter = 0; // 用于记录最大直径

    @LeetCode(
        level = Level.EASY,
        title = "543. 二叉树的直径",
        source = "https://leetcode.cn/problems/diameter-of-binary-tree/",
        point = { Point.BINARY_TREE, Point.RECURSION }
    )
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeMaxDepth(root);
        return diameterOfBinaryTreeMaxDiameter;
    }

    private int diameterOfBinaryTreeMaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = diameterOfBinaryTreeMaxDepth(root.left);
        int rightDepth = diameterOfBinaryTreeMaxDepth(root.right);

        // 计算通过当前节点的直径并更新 maxDiameter
        diameterOfBinaryTreeMaxDiameter = Math.max(
            diameterOfBinaryTreeMaxDiameter,
            leftDepth + rightDepth
        );

        // 返回当前节点的最大深度
        return Math.max(leftDepth, rightDepth) + 1;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "102. 二叉树的层序遍历",
        source = "https://leetcode.cn/problems/binary-tree-level-order-traversal/",
        point = { Point.BINARY_TREE, Point.BFS }
    )
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size(); // 记录当前层的节点数量
            List<Integer> current = new ArrayList<>();
            for (int i = 0; i < size; i++) { // 只处理当前层的节点
                TreeNode node = queue.poll();
                current.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(current);
        }

        return res;
    }

    @LeetCode(
        level = Level.EASY,
        title = "108. 将有序数组转换为二叉搜索树",
        source = "https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/",
        point = { Point.BINARY_TREE, Point.RECURSION, Point.DIVIDE_AND_CONQUER }
    )
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2; // 防止溢出
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBSTHelper(nums, left, mid - 1);
        node.right = sortedArrayToBSTHelper(nums, mid + 1, right);
        return node;
    }

    int rightSideViewMaxDepth; // 记录当前最大深度
    ArrayList<Integer> rightSideViewAns; // 存储结果

    @LeetCode(
        level = Level.MEDIUM,
        title = "199. 二叉树的右视图",
        source = "https://leetcode.cn/problems/binary-tree-right-side-view/",
        point = { Point.BINARY_TREE, Point.DFS }
    )
    public List<Integer> rightSideView(TreeNode root) {
        rightSideViewAns = new ArrayList<>();
        dfs(root, 1);
        return rightSideViewAns;
    }

    void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 如果当前节点的深度大于最大深度，则将其值添加到结果列表中，并更新最大深度
        if (depth > rightSideViewMaxDepth) {
            rightSideViewAns.add(root.val);
            rightSideViewMaxDepth = depth;
        }
        // 优先遍历右子树
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "114. 二叉树展开为链表",
        source = "https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/",
        point = { Point.BINARY_TREE, Point.RECURSION }
    )
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left); // 递归展开左子树
        flatten(root.right); // 递归展开右子树

        TreeNode tmp = root.right; // 暂存右子树
        root.right = root.left; // 将左子树设置为右子树
        root.left = null; // 将左子树置空

        TreeNode current = root;
        while (current.right != null) {
            current = current.right; // 找到当前右子树的末端
        }
        current.right = tmp; // 将暂存的右子树接到当前右子树的末端
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "105. 从前序与中序遍历序列构造二叉树",
        source = "https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/",
        point = { Point.BINARY_TREE, Point.RECURSION, Point.DIVIDE_AND_CONQUER }
    )
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        return buildTreeHelper(
            preorder,
            inorder,
            0,
            preorder.length - 1,
            0,
            inorder.length - 1
        );
    }

    // 查找元素在数组中的索引
    int indexOf(int[] nums, int val) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                return i;
            }
        }
        return -1;
    }

    // 辅助函数，用于递归构建二叉树
    public TreeNode buildTreeHelper(
        int[] preorder,
        int[] inorder,
        int preStart,
        int preEnd,
        int inStart,
        int inEnd
    ) {
        // 递归终止条件：子树为空
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        // 前序遍历的第一个元素为根节点的值
        int val = preorder[preStart];
        // 在中序遍历中查找根节点的索引
        int indexInOrder = indexOf(inorder, val);

        // 创建根节点
        TreeNode root = new TreeNode(val);

        // 左子树的节点数量
        int leftTreeNodeNums = indexInOrder - inStart;

        // 递归构建左子树
        root.left = buildTreeHelper(
            preorder,
            inorder,
            preStart + 1,
            preStart + leftTreeNodeNums,
            inStart,
            indexInOrder - 1
        );

        // 递归构建右子树
        root.right = buildTreeHelper(
            preorder,
            inorder,
            preStart + leftTreeNodeNums + 1,
            preEnd,
            indexInOrder + 1,
            inEnd
        );

        return root;
    }

    int pathSumAns; // 结果

    Map<Long, Integer> pathSumPrefix = new HashMap<Long, Integer>(); // 前缀和及其出现次数

    @LeetCode(
        level = Level.MEDIUM,
        title = "437. 路径总和 III",
        source = "https://leetcode.cn/problems/path-sum-iii/",
        point = { Point.BINARY_TREE, Point.DFS }
    )
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        // 初始化前缀和，0出现一次
        pathSumPrefix.put(0L, 1);
        // 深度优先搜索
        dfs(root, (long) targetSum, 0L);

        return pathSumAns;
    }

    private void dfs(TreeNode node, long targetSum, long currentSum) {
        if (node == null) {
            return;
        }

        // 更新当前前缀和
        currentSum += node.val;

        // 计算以当前节点结尾的路径中，和为targetSum的路径数量
        pathSumAns += pathSumPrefix.getOrDefault(currentSum - targetSum, 0);
        // 更新前缀和及其出现次数
        pathSumPrefix.merge(currentSum, 1, (p, c) -> p + c);
        // 递归处理左右子树
        dfs(node.left, targetSum, currentSum);
        dfs(node.right, targetSum, currentSum);
        // 回溯，移除当前节点对前缀和的影响
        pathSumPrefix.merge(currentSum, -1, (p, c) -> p + c);
    }
}
