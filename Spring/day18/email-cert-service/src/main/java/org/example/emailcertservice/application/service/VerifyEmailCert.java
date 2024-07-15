package org.example.emailcertservice.application.service;

import lombok.RequiredArgsConstructor;
import org.example.emailcertservice.application.port.in.VerifyEmailCertCommand;
import org.example.emailcertservice.application.port.in.VerifyEmailCertUseCase;
import org.example.emailcertservice.application.port.out.EmailCertPort;
import org.example.emailcertservice.common.UseCase;
import org.example.emailcertservice.domain.EmailCert;
import org.springframework.stereotype.Component;

@UseCase
@RequiredArgsConstructor
public class VerifyEmailCert implements VerifyEmailCertUseCase {
    private final EmailCertPort emailCertPort;
    @Override
    public boolean verifyEmailCert(VerifyEmailCertCommand command) {
        EmailCert emailCert = EmailCert.builder()
                .email(command.getEmail())
                .uuid(command.getUuid())
                .build();
        return emailCertPort.findEmailCert(emailCert);
    }
}
