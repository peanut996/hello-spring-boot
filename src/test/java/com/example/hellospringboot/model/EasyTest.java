package com.example.hellospringboot.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EasyTest {

    static Problem problem;

    @BeforeAll
    static void setUp() {
        problem = new Problem();
    }


    @Test
    void canMakeArithmeticProgression() {
        int[] nums = new int[]{1, 2, 4};
        assertFalse(problem.canMakeArithmeticProgression(nums));

        nums = new int[]{1, 3, 5};
        assertTrue(problem.canMakeArithmeticProgression(nums));
    }

    @Test
    void modifiedMatrix() {
        int[][] matrix = new int[][]{{1, 2, -1}, {4, -1, 6}, {7, 8, 9}};
        problem.modifiedMatrix(matrix);

        assertEquals("[[1, 2, 9], [4, 8, 6], [7, 8, 9]]", Arrays.deepToString(matrix));
    }


    @Test
    void easy88Merge() {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        problem.merge(nums1, 3, nums2, 3);
        assertEquals("[1, 2, 2, 3, 5, 6]", Arrays.toString(nums1));
    }
}