package org.example.emailcertservice.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import org.example.emailcertservice.application.port.in.SendEmailCommand;
import org.example.emailcertservice.application.port.in.SendEmailUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SignupMemberKafkaConsumer {
    private final SendEmailUseCase sendEmailUseCase;
    @KafkaListener(topics = "member_signup", groupId = "group_1")
    public void receive(String email) throws IOException {
        SendEmailCommand command = SendEmailCommand.builder()
                .email(email)
                .build();
        sendEmailUseCase.sendEmail(command);

    }
}
