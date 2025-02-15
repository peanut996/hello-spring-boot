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
}
