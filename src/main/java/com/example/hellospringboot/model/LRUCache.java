package com.example.hellospringboot.model;


public class LRUCache {

    class Node {
        int key;
        int value;
        Node next;

        Node() {
            value = -1;
            key = -1;
        }
    }

    Node head;
    int cap;

    public LRUCache(int capacity) {
        cap = capacity;
        Node dummy = new Node();
        Node current = dummy;
        for (int i = 0; i < capacity; i++) {
            current.next = new Node();
            current = current.next;
        }
        head = dummy.next;
    }

    public int get(int key) {
        Node current = head;
        Node prev = null;
        while (current != null) {
            if (current.key == key) {
                if (prev != null) {
                    prev.next = current.next;
                    current.next = head;
                    head = current;
                }
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node current = head;

        Node exist = null;
        Node prev = null;
        while (current != null) {
            if (current.key == key) {
                current.value = value;
                if (prev != null) {
                    prev.next = current.next;
                    current.next = head;
                    head = current;
                }

                return;
            }
            prev = current;
            current = current.next;
        }

        Node node = new Node();
        node.key = key;
        node.value = value;
        node.next = head;
        head = node;


        current = head;
        for (int i = 1; i < cap; i++) {
            current = current.next;
            if (current == null) {
                return;
            }
        }
        current.next = null;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */