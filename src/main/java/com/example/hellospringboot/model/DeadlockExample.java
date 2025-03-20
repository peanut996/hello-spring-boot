package com.example.hellospringboot.model;

class NonReentrantLock {
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}

public class DeadlockExample {
    private NonReentrantLock lock = new NonReentrantLock();

    public void outer() throws InterruptedException {
        lock.lock();
        try {
            inner();
        } finally {
            lock.unlock();
        }
    }

    public void inner() throws InterruptedException {
        lock.lock(); // 尝试再次获取锁，导致死锁
        try {
            // ...
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadlockExample example = new DeadlockExample();
        example.outer();
    }
}