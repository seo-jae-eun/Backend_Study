package org.example.day17.adapter.out.mail;

import lombok.RequiredArgsConstructor;
import org.example.day17.application.port.out.SignupMemberEmailCertificatePort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SignupMemberEmailCertificateAdapter implements SignupMemberEmailCertificatePort {
    private final JavaMailSender emailSender;

    @Override
    public String sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[Pochacco] Welcome to join !!");
        String uuid = UUID.randomUUID().toString();
        message.setText("http://localhost/api/member/verify?email="+email+"&uuid="+uuid);

        emailSender.send(message);

        return uuid;
    }
}
