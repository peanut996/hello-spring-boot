package com.example.hellospringboot.model;

import com.example.hellospringboot.annotation.LeetCode;
import com.example.hellospringboot.annotation.LeetCode.Level;
import com.example.hellospringboot.annotation.LeetCode.Point;
import java.util.HashMap;
import java.util.Map;

@LeetCode(
    level = Level.MEDIUM,
    title = "146. LRU 缓存",
    source = "https://leetcode.cn/problems/lru-cache/",
    point = { Point.HASH, Point.DOUBLY_LINKED_LIST }
)
public class LRUCache {

    class Node {

        int key;
        int value;
        Node prev;
        Node next;

        Node() {
            value = -1;
            key = -1;
        }

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Map<Integer, Node> map;
    int cap;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        cap = capacity;
        head = new Node();
        tail = new Node();
        map = new HashMap<>();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            moveToHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            Node node = new Node(key, value);
            addToHead(node);
            map.put(key, node);

            if (map.size() > cap) {
                removeTail();
            }
        }
    }

    void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    void removeTail() {
        Node remove = tail.prev;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        map.remove(remove.key);
    }

    void addToHead(Node node) {
        // node 与head.next 建立连接
        node.next = head.next;
        head.next.prev = node;
        // node与head建立连接
        node.prev = head;
        head.next = node;
    }

    void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
