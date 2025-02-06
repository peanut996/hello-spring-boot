package com.example.hellospringboot.model;

import com.example.hellospringboot.annotation.LeetCode;
import com.example.hellospringboot.annotation.LeetCode.Level;
import com.example.hellospringboot.annotation.LeetCode.Point;
import java.util.HashMap;


@LeetCode(
        level = Level.MEDIUM,
        title = "208. 实现 Trie (前缀树)",
        source = "https://leetcode.cn/problems/implement-trie-prefix-tree/",
        point = { Point.TRIE }
)
class Trie {
    boolean end; // 标记是否为单词结尾

    HashMap<Character, Trie> children; // 子节点，使用HashMap存储

    public Trie() {
        children = new HashMap<>(); // 初始化子节点
    }

    // 插入单词
    public void insert(String word) {
        Trie current = this; // 从根节点开始
        for (char c : word.toCharArray()) { // 遍历单词的每个字符
            if (!current.children.containsKey(c)) { // 如果当前节点没有该字符的子节点
                current.children.put(c,  new Trie()); // 创建新的子节点
            }
            current = current.children.get(c); // 移动到子节点
        }
        current.end = true; // 标记当前节点为单词结尾
    }

    // 查找单词
    public boolean search(String word) {
        Trie current = this; // 从根节点开始
        for (char c : word.toCharArray()) { // 遍历单词的每个字符
            if (!current.children.containsKey(c)) { // 如果当前节点没有该字符的子节点
                return false; // 单词不存在
            }
            current = current.children.get(c); // 移动到子节点
        }

        return current.end; // 判断是否为单词结尾
    }

    // 查找前缀
    public boolean startsWith(String prefix) {
        Trie current = this; // 从根节点开始
        for (char c : prefix.toCharArray()) { // 遍历前缀的每个字符
            if (!current.children.containsKey(c)) { // 如果当前节点没有该字符的子节点
                return false; // 前缀不存在
            }
            current = current.children.get(c); // 移动到子节点
        }
        return true; // 前缀存在
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */