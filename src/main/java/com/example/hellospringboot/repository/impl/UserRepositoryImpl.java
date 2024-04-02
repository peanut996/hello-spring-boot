package com.example.hellospringboot.repository.impl;

import com.example.hellospringboot.mapper.UserMapper;
import com.example.hellospringboot.model.User;
import com.example.hellospringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }
}
