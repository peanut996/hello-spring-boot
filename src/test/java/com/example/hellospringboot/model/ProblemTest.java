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