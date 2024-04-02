package com.example.hellospringboot.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class User {
        private Integer id;
        private String username;
        private String email;
        private String password;
        private LocalDateTime createdAt;
}
