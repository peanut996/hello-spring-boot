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
}
