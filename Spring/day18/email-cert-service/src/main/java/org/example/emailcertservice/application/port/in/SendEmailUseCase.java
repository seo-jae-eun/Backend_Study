package org.example.emailcertservice.application.port.in;

public interface SendEmailUseCase {
    void sendEmail(SendEmailCommand command);
}
