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

    @Test
    void numSmallerByFrequency() {
        String[] queries = new String[]{"cbd"};
        String[] words = new String[]{"zaaaz"};

        int[] res = problem.numSmallerByFrequency(queries, words);
        assertArrayEquals(new int[]{1}, res);
    }


    @Test
    void threeSum() {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        problem.threeSum(nums);
    }

    @Test
    void LRU() {
        LRUCache cache = new LRUCache(1);
        cache.put(2,1);
        assert  1 == cache.get(2);
        cache.put(3,2);
        assert -1 == cache.get(2);
        assert 2 == cache.get(3);
    }

    @Test
    void decodeString() {
        String res = problem.decodeString("3[a]2[bc]");
        assert "aaabcbc".equals(res);

        assert "accaccacc".equals(problem.decodeString("3[a2[c]]"));
    }
}