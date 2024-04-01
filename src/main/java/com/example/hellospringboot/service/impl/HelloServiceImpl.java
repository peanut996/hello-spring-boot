package com.example.hellospringboot.service.impl;

import com.example.hellospringboot.service.HelloService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String ping() {
        return "pong";
    }
}
