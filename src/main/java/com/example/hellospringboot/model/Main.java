package com.example.hellospringboot.model;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            var line = sc.nextLine();
            var nums = line.split(" ");
            var a = nums[0];
            var b = nums[1];
            System.out.println(a + b);
            System.out.println();
        }
    }

    public int[] mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int[] array : arrays) {
            for (int i : array) {
                minHeap.offer(i);
            }
        }

        int size = minHeap.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = minHeap.poll();
        }

        return res;
    }
}
