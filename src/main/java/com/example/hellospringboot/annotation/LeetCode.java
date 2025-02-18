package com.example.hellospringboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(value = { ElementType.TYPE, ElementType.METHOD })
public @interface LeetCode {
    enum Level {
        EASY,
        MEDIUM,
        HARD,
    }

    enum Point {
        /**
         * 滑动窗口
         */
        SLIDE_WINDOW,
        /**
         * 字典树
         */
        TRIE,

        /**
         * 哈希表
         */
        HASH,

        /**
         * 二分查找
         */
        BINARY_SEARCH,

        /**
         * 矩阵
         */
        MATRIX,

        /**
         * 前缀和
         */
        PREFIX,

        /**
         * 动态规划
         */
        DP,

        /**
         * 双指针
         */
        TWO_POINTER,

        /**
         * 数组
         */
        ARRAY,

        /**
         * 双指针
         */
        TWO_POINTERS,

        /**
         * 链表
         */
        LINKED_LIST,

        /**
         * 双向链表
         */
        DOUBLY_LINKED_LIST,

        /**
         * 排序
         */
        SORT,

        /**
         * 合并排序
         */
        MERGE_SORT,

        /**
         * 堆
         */
        HEAP,

        /**
         * 分治法
         */
        DIVIDE_AND_CONQUER,

        /**
         * 树
         */
        TREE,

        /**
         * 二叉树
         */
        BINARY_TREE,

        /**
         * 递归
         */
        RECURSION,

        /**
         * 二叉搜索树
         */
        BINARY_SEARCH_TREE,

        /**
         * 遍历
         */
        TRAVERSAL,

        /**
         * 莫里斯遍历
         */
        MORRIS_TRAVERSAL,

        /**
         * 深度优先搜索
         */
        DFS,

        /**
         * 广度优先搜索
         */
        BFS,

        /**
         * 图
         */
        GRAPH,

        /**
         * 拓扑排序
         */
        TOPOLOGICAL_SORT,

        /**
         * 回溯
         */
        BACKTRACKING,

        /**
         * 字符串
         */
        STRING,

        /**
         * 栈
         */
        STACK,

        /**
         * 单调栈
         */
        MONOTONIC_STACK,

        /**
         * 贪心
         */
        GREEDY,

        /**
         * 动态规划
         */
        DYNAMIC_PROGRAMMING,

        /**
         * 优先队列
         */
        PRIORITY_QUEUE,

        /**
         * 哈希表
         */
        HASH_MAP,
    }

    Level level();

    Point[] point() default {};

    String source() default "";

    String title() default "";
}
