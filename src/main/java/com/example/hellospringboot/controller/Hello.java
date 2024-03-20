package com.example.hellospringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Hello {

    @GetMapping("/ping")
    String ping() {
        return "pong";
    }}
