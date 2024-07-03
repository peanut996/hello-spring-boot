package com.example.hellospringboot.base;

import com.example.hellospringboot.model.Cat;
import com.example.hellospringboot.model.LifeCycle;
import com.example.hellospringboot.model.User;
import org.junit.jupiter.api.Test;

public class LifeCycleTest {
    /**
     * 浅拷贝测试
     */
    @Test
    void test() {
        System.out.println("start");
        new LifeCycle();
        System.out.println("done");
    }
}
