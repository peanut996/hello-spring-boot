package com.example.hellospringboot.controller;

import com.example.hellospringboot.annotation.Lock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/lock")
@Slf4j
public class DistributedLockController {
    
    private final AtomicInteger counter = new AtomicInteger(0);
    
    @GetMapping("/test1")
    @Lock(key = "test-lock", waitTime = 3000)
    public String testLock1() {
        log.info("Executing testLock1, counter: {}", counter.get());
        try {
            Thread.sleep(2000);
            int value = counter.incrementAndGet();
            log.info("testLock1 completed, counter: {}", value);
            return "testLock1 completed (auto-release), counter: " + value;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "interrupted";
        }
    }
    
    @GetMapping("/test2/{userId}")
    @Lock(key = "user-lock-#userId", waitTime = 3000)
    public String testLock2(@PathVariable String userId) {
        log.info("Executing testLock2 for userId: {}", userId);
        try {
            Thread.sleep(1000);
            return "testLock2 completed (auto-release) for user: " + userId;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "interrupted";
        }
    }
    
    @GetMapping("/test3")
    @Lock(leaseTime = 8000)
    public String testLock3() {
        log.info("Executing testLock3 with 8s lease time");
        try {
            Thread.sleep(1000);
            return "testLock3 completed with 8s lease time";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "interrupted";
        }
    }
    
    @GetMapping("/counter")
    public String getCounter() {
        return "Current counter: " + counter.get();
    }
}