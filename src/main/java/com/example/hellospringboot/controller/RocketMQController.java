package com.example.hellospringboot.controller;

import com.example.hellospringboot.model.MessagePayload;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/rocketmq")
@RequiredArgsConstructor
public class RocketMQController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @GetMapping("/send")
    public String sendMessage() {
        String topic = "test-topic:string-tag";
        String message = "Hello, RocketMQ! (with string-tag)";
        rocketMQTemplate.convertAndSend(topic, message);
        return "Message sent: " + message;
    }

    @GetMapping("/send-object")
    public MessagePayload sendObjectMessage() {
        String topic = "test-topic:object-tag";
        MessagePayload payload = new MessagePayload(
                UUID.randomUUID().toString(),
                "This is an object message. (with object-tag)",
                System.currentTimeMillis()
        );
        rocketMQTemplate.convertAndSend(topic, payload);
        return payload;
    }
}
