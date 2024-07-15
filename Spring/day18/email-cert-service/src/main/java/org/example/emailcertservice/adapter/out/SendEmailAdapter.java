package org.example.emailcertservice.adapter.out;

import lombok.RequiredArgsConstructor;
import org.example.emailcertservice.application.port.out.SendEmailPort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SendEmailAdapter implements SendEmailPort {
    private final JavaMailSender emailSender;

    @Override
    public String sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[내 사이트] 가입 환영");
        String uuid = UUID.randomUUID().toString();
        message.setText("http://localhost:8081/emailcert/verify?email="+email+"&uuid="+uuid);

        emailSender.send(message);

        return uuid;
    }
}
