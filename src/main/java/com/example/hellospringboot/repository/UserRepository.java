package com.example.hellospringboot.repository;

import com.example.hellospringboot.model.User;

import java.util.List;

public interface UserRepository {
    List<User> list();
}
