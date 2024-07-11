package org.example.day17.adapter.out.kafka;

import lombok.RequiredArgsConstructor;
import org.example.day17.application.port.out.SignupMemberNotificationPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

// external adapter
@Component
@RequiredArgsConstructor
public class MemberKafkaProducer implements SignupMemberNotificationPort {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendSignupMessage(String message) {
        kafkaTemplate.send("member_signup", message);
    }
}
