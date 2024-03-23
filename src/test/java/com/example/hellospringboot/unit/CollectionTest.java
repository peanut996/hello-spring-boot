package com.example.hellospringboot.unit;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;

public class CollectionTest {

    @Test
    void map() {
        Hashtable hashtable = new Hashtable();
        ConcurrentHashMap hashMap = new ConcurrentHashMap<>();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        TreeMap treeMap = new TreeMap();
    }

    @Test
    void list() {
        ArrayList arrayList = new ArrayList();
        ArrayDeque arrayDeque = new ArrayDeque();

        Vector vector = new Vector<>();
        Stack stack = new Stack();
    }

    @Test
    void set() {
        HashSet hashSet = new HashSet();
        LinkedHashSet linkedHashSet = new LinkedHashSet<>();
        TreeSet treeSet = new TreeSet();
    }

    @Test
    void iterator() {
        ListIterator listIterator = new ArrayList<>().listIterator();
    }

    @Test
    void concurrent() {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        BlockingQueue blockingQueue = new ArrayBlockingQueue(10);
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();
    }
}
