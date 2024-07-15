package org.example.emailcertservice.application.service;

import lombok.RequiredArgsConstructor;
import org.example.emailcertservice.adapter.out.EmailCertEntity;
import org.example.emailcertservice.application.port.in.SendEmailCommand;
import org.example.emailcertservice.application.port.in.SendEmailUseCase;
import org.example.emailcertservice.application.port.out.EmailCertPort;
import org.example.emailcertservice.application.port.out.SendEmailPort;
import org.example.emailcertservice.common.UseCase;
import org.example.emailcertservice.domain.EmailCert;
import org.springframework.stereotype.Component;

@UseCase
@RequiredArgsConstructor
public class SendEmail implements SendEmailUseCase {
    private final SendEmailPort sendEmailPort;
    private final EmailCertPort persistencePort;
    @Override
    public void sendEmail(SendEmailCommand command) {
        String uuid = sendEmailPort.sendEmail(command.getEmail());

        EmailCert emailCert = EmailCert.builder()
                .email(command.getEmail())
                .uuid(uuid)
                .build();
        persistencePort.saveEmailCert(emailCert);

    }
}
