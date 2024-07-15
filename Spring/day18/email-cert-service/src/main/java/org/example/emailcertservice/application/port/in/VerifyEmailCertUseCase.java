package org.example.emailcertservice.application.port.in;

public interface VerifyEmailCertUseCase {
    boolean verifyEmailCert(VerifyEmailCertCommand command);
}
