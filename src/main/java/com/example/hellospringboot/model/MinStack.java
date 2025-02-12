package com.example.hellospringboot.model;

import com.example.hellospringboot.annotation.LeetCode;
import com.example.hellospringboot.annotation.LeetCode.Level;
import com.example.hellospringboot.annotation.LeetCode.Point;
import java.util.Stack;

@LeetCode(
    level = Level.MEDIUM,
    title = "155. 最小栈",
    source = "https://leetcode.cn/problems/min-stack/",
    point = { Point.STACK }
)
public class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        int val = stack.pop();
        if (val == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
