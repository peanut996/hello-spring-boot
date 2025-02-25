package com.example.hellospringboot.model;

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
}
