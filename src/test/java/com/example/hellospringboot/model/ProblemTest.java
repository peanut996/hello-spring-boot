package com.example.hellospringboot.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProblemTest {

    static Problem problem = new Problem();

    @BeforeAll
    static void setUp() {
        problem = new Problem();
    }

    /**
     * 2009. 使数组连续的最少操作数
     * <p>
     * 给你一个整数数组 nums 。每一次操作中，你可以将 nums 中 任意 一个元素替换成 任意 整数。
     * <p>
     * 如果 nums 满足以下条件，那么它是 连续的 ：
     * <p>
     * nums 中所有元素都是 互不相同 的。
     * nums 中 最大 元素与 最小 元素的差等于 nums.length - 1 。
     * <p>
     * 比方说，nums = [4, 2, 5, 3] 是 连续的 ，但是 nums = [1, 2, 3, 5, 6] 不是连续的 。
     * <p>
     * 请你返回使 nums 连续 的 最少 操作次数
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [4,2,5,3]
     * 输出：0
     * 解释：nums 已经是连续的了。
     * <p>
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3,5,6]
     * 输出：1
     * 解释：一个可能的解是将最后一个元素变为 4 。
     * 结果数组为 [1,2,3,5,4] ，是连续数组。
     * <p>
     * 示例 3：
     * <p>
     * 输入：nums = [1,10,100,1000]
     * 输出：3
     * 解释：一个可能的解是：
     * - 将第二个元素变为 2 。
     * - 将第三个元素变为 3 。
     * - 将第四个元素变为 4 。
     * 结果数组为 [1,2,3,4] ，是连续数组。
     */
    @Test
    void minOperations() {
        int[] nums = new int[]{1, 2, 3, 5, 6};
        Assertions.assertEquals(1, problem.minOperations(nums));

        nums = new int[]{2, 4, 3, 5};
        Assertions.assertEquals(0, problem.minOperations(nums));

        nums = new int[]{1, 10, 100, 1000};
        Assertions.assertEquals(3, problem.minOperations(nums));
    }

    @Test
    void maximumCount() {
        int[] nums = new int[]{-2, -1, -1, 1, 2, 3};
        assertEquals(3, problem.maximumCount(nums));
    }
}