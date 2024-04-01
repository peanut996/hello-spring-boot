package com.example.hellospringboot.controller;

import com.example.hellospringboot.annotation.MeasureExecutionTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Hello {

    @GetMapping("/ping")
    @MeasureExecutionTime
    String ping() {
        return "pong";
    }}
