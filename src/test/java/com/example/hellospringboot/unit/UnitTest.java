package com.example.hellospringboot.unit;

import org.junit.jupiter.api.Test;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class UnitTest {

    @Test
    void test() {
        Hashtable hashtable = new Hashtable();
        ConcurrentHashMap hashMap = new ConcurrentHashMap<>();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        System.out.println("hello");
    }
}
