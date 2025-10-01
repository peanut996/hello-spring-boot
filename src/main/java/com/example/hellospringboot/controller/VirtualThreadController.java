package com.example.hellospringboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/virtual-thread")
@Slf4j
public class VirtualThreadController {

    private final VirtualThreadService virtualThreadService;

    public VirtualThreadController(VirtualThreadService virtualThreadService) {
        this.virtualThreadService = virtualThreadService;
    }

    @GetMapping("/demo")
    public String demo() {
        log.info("Main thread: {}", Thread.currentThread());

        // 使用虚拟线程执行异步任务
        virtualThreadService.asyncTask("Task 1");
        virtualThreadService.asyncTask("Task 2");
        virtualThreadService.asyncTask("Task 3");

        return "Virtual thread demo started. Check logs for thread information.";
    }

    @GetMapping("/compare")
    public String compare() {
        long startTime = System.currentTimeMillis();

        // 使用虚拟线程执行多个并发任务
        CompletableFuture<String> future1 = virtualThreadService.asyncTaskWithResult("Job 1", 1000);
        CompletableFuture<String> future2 = virtualThreadService.asyncTaskWithResult("Job 2", 1500);
        CompletableFuture<String> future3 = virtualThreadService.asyncTaskWithResult("Job 3", 2000);

        // 等待所有任务完成
        CompletableFuture.allOf(future1, future2, future3).join();

        long endTime = System.currentTimeMillis();

        return String.format("All tasks completed in %d ms using virtual threads", endTime - startTime);
    }

    @GetMapping("/info")
    public String info() {
        Thread currentThread = Thread.currentThread();
        return String.format(
            "Thread Name: %s, Is Virtual: %s, Thread ID: %d",
            currentThread.getName(),
            currentThread.isVirtual(),
            currentThread.threadId()
        );
    }
}

@Service
@Slf4j
class VirtualThreadService {

    @Async
    public void asyncTask(String taskName) {
        Thread currentThread = Thread.currentThread();
        log.info("{} - Thread: {}, Is Virtual: {}, Thread ID: {}",
            taskName,
            currentThread.getName(),
            currentThread.isVirtual(),
            currentThread.threadId()
        );

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        log.info("{} completed", taskName);
    }

    @Async
    public CompletableFuture<String> asyncTaskWithResult(String jobName, long sleepMs) {
        Thread currentThread = Thread.currentThread();
        log.info("{} started - Thread: {}, Is Virtual: {}",
            jobName,
            currentThread.getName(),
            currentThread.isVirtual()
        );

        try {
            Thread.sleep(sleepMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.completedFuture(jobName + " interrupted");
        }

        String result = String.format("%s completed after %d ms", jobName, sleepMs);
        log.info(result);
        return CompletableFuture.completedFuture(result);
    }
}
