package com.example.hellospringboot.model;

import com.example.hellospringboot.annotation.Easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 729. 我的日程安排表 I
 * <a href="https://leetcode.cn/problems/my-calendar-i/description/">729. 我的日程安排表 I</a>
 */
@Easy
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