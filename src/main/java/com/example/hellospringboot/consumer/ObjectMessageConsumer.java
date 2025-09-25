package com.example.hellospringboot.consumer;

import com.example.hellospringboot.model.MessagePayload;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "test-topic", consumerGroup = "my-object-consumer-group", selectorExpression = "object-tag")
public class ObjectMessageConsumer implements RocketMQListener<MessagePayload> {

    @Override
    public void onMessage(MessagePayload message) {
        System.out.printf("------- Received object message: %s %n", message);
    }
}
