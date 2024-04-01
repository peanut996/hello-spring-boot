package com.example.hellospringboot.service.impl;

import com.example.hellospringboot.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String ping() {
        return "pong";
    }
}
