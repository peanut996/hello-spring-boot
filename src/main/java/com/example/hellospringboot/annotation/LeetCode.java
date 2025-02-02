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
        BIN_SEARCH,

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
    }

    Level level();

    Point[] point() default {};

    String source() default "";

    String title() default "";
}
