package com.example.hellospringboot.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EasyTest {

    static Easy problem;

    @BeforeAll
    static void setUp() {
        problem = new Easy();
    }


    @Test
    void canMakeArithmeticProgression() {
        int[] nums = new int[]{1,2,4};
        assertFalse(problem.canMakeArithmeticProgression(nums));

        nums = new int[]{1,3,5};
        assertTrue(problem.canMakeArithmeticProgression(nums));
    }

    @Test
    void modifiedMatrix() {
        int[][] matrix = new int[][]{{1, 2, -1}, {4, -1, 6}, {7, 8, 9}};
        problem.modifiedMatrix(matrix);

        assertEquals("[[1, 2, 9], [4, 8, 6], [7, 8, 9]]", Arrays.deepToString(matrix));
    }
}