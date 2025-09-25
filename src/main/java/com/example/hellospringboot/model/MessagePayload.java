package com.example.hellospringboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagePayload implements Serializable {
    private static final long serialVersionUID = -529579397394194841L;

    private String id;
    private String content;
    private long timestamp;
}
