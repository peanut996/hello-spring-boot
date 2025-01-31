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

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
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
        ListNode dummy = head;
        while (dummy != null) {
            ListNode tmp = dummy.next;
            dummy.next = prev;
            prev = dummy;
            dummy = tmp;
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
}
