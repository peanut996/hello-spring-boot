package com.example.hellospringboot.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cat implements Cloneable {
    private String name;
    private User owner;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}


