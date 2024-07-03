package com.example.hellospringboot.unit;

import com.example.hellospringboot.model.Cat;
import com.example.hellospringboot.model.User;
import org.junit.jupiter.api.Test;

public class CloneTest {
    /**
     * 浅拷贝测试
     *
     * @throws CloneNotSupportedException
     */
    @Test
    void clone_test() throws CloneNotSupportedException {
        Cat cat = new Cat();
        User user = new User();
        user.setUsername("1");
        cat.setOwner(user);

        Cat clonedCat = (Cat) cat.clone();
        user.setUsername("2");
        System.out.println(clonedCat.getOwner().getUsername());

    }
}
