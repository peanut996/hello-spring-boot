package com.example.hellospringboot.model.solution;

import com.example.hellospringboot.annotation.LeetCode;
import com.example.hellospringboot.annotation.LeetCode.Level;
import java.util.ArrayList;
import java.util.List;

@LeetCode(level = Level.EASY, title = "729. 我的日程安排表 I", source = "https://leetcode-cn.com/problems/my-calendar-i/")
public class MyCalendar {

    static class Book {
        int start;
        int end;

        public Book(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private List<Book> books;

    public MyCalendar() {
        books = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (Book existBook : books) {
            boolean canBook = (start >= existBook.end || end <= existBook.start);
            if (!canBook) {
                return false;
            }
        }
        this.storeNewBook(start, end);
        return true;
    }

    public void storeNewBook(int start, int end) {
        books.add(new Book(start, end));
    }
}