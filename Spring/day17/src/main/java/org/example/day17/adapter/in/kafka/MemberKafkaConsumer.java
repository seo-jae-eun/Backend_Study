package org.example.day17.adapter.in.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MemberKafkaConsumer {
    @KafkaListener(topics = "test", groupId = "group_1")
    public void receive(String message) {
        System.out.println(message);
    }
}
