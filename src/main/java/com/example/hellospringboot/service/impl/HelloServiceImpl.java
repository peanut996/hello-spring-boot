package com.example.hellospringboot.service.impl;

import com.example.hellospringboot.model.User;
import com.example.hellospringboot.repository.UserRepository;
import com.example.hellospringboot.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HelloServiceImpl implements HelloService {

    private final UserRepository userRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<User> ping() {
        return userRepository.list();
    }
}
