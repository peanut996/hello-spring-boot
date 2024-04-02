package com.example.hellospringboot.controller;

import com.example.hellospringboot.annotation.MeasureExecutionTime;
import com.example.hellospringboot.model.User;
import com.example.hellospringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    public final ApplicationContext applicationContext;
    public final UserService userService;

    @GetMapping("/users")
    @MeasureExecutionTime
    List<User> ping() {
        return userService.list();
    }}
