package org.example.memberhex.adapter.out.kafka;

import lombok.RequiredArgsConstructor;
import org.example.memberhex.application.port.out.SignupMemberNotificationPort;
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
