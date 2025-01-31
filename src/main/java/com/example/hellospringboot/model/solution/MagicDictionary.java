package com.example.hellospringboot.model.solution;

import com.example.hellospringboot.annotation.LeetCode;
import com.example.hellospringboot.annotation.LeetCode.Level;
import com.example.hellospringboot.annotation.LeetCode.Point;

@LeetCode(
    level = Level.MEDIUM,
    point = Point.TRIE,
    title = "676. 实现一个魔法字典",
    source = "https://leetcode.cn/problems/implement-magic-dictionary/description/"
)
public class MagicDictionary {

    Trie root;

    public MagicDictionary() {
        this.root = new Trie();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            insert(word);
        }
    }

    private void insert(String word) {
        Trie node = this.root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.exist = true;
    }

    public boolean search(String searchWord) {
        return dfs(searchWord.toCharArray(), root, 0, false);
    }

    private boolean dfs(char[] word, Trie node, int index, boolean isMatch) {
        if (index >= word.length) {
            return isMatch && node != null && node.exist;
        }

        if (node.children[word[index] - 'a'] != null) {
            if (
                dfs(word, node.children[word[index] - 'a'], index + 1, isMatch)
            ) {
                return true;
            }
        }

        if (!isMatch) {
            for (int i = 0; i < 26; i++) {
                if (i != word[index] - 'a' && node.children[i] != null) {
                    if (dfs(word, node.children[i], index + 1, true)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

class Trie {

    Trie[] children;
    boolean exist;

    public Trie() {
        this.children = new Trie[26];
        this.exist = false;
    }
}
/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
