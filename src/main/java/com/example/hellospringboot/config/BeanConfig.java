package com.example.hellospringboot.config;

import com.example.hellospringboot.model.Problem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public Problem problem() {
        return new Problem();
    }
}
