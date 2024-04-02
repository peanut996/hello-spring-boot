package com.example.hellospringboot.controller;

import com.example.hellospringboot.annotation.MeasureExecutionTime;
import com.example.hellospringboot.model.User;
import com.example.hellospringboot.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class Hello {

    public final ApplicationContext applicationContext;
    public final HelloService helloService;

    @GetMapping("/ping")
    @MeasureExecutionTime
    List<User> ping() {
        return helloService.ping();
    }}
