package com.example.hellospringboot.model;

import com.example.hellospringboot.annotation.LeetCode;
import com.example.hellospringboot.annotation.LeetCode.*;
import java.util.*;

@LeetCode(
    level = Level.MEDIUM,
    title = "295. 数据流的中位数",
    source = "https://leetcode.cn/problems/find-median-from-data-stream/",
    point = { Point.HEAP }
)
class MedianFinder {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
    int count;

    public MedianFinder() {}

    /**
     * 向数据结构中添加数字
     * @param num 要添加的数字
     */
    public void addNum(int num) {
        if (maxHeap.size() == minHeap.size()) {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
    }

    /**
     * 查找当前数据流的中位数
     * @return 中位数
     */
    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "55. 跳跃游戏",
        source = "https://leetcode.cn/problems/jump-game/",
        point = { Point.ARRAY }
    )
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            if (i <= maxReach) {
                // 更新能到达的最远距离
                maxReach = Math.max(maxReach, i + nums[i]);
            }
        }
        // 判断是否能到达数组末尾
        if (nums.length - 1 <= maxReach) {
            return true;
        }
        return false;
    }

    @LeetCode(
        level = Level.MEDIUM,
        title = "45. 跳跃游戏 II",
        source = "https://leetcode.cn/problems/jump-game-ii/",
        point = { Point.ARRAY }
    )
    public int jump(int[] nums) {
        int jumps = 0; // 跳跃次数
        int currentJumpEnd = 0; // 当前跳跃能到达的边界
        int farthest = 0; // 在当前跳跃范围内，能到达的最远位置

        for (int i = 0; i < nums.length - 1; i++) { // 注意：这里是 nums.length - 1，因为到达最后一个位置不需要再跳
            // 不断更新“当前跳跃范围内能到达的最远位置”
            farthest = Math.max(farthest, i + nums[i]);

            // 到达当前跳跃边界
            if (i == currentJumpEnd) {
                jumps++; // 跳跃次数加 1
                currentJumpEnd = farthest; // 更新当前跳跃边界为“当前跳跃范围内能到达的最远位置”
            }
        }

        return jumps;
    }
}
