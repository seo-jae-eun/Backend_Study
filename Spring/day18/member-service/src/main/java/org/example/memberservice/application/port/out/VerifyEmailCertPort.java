package org.example.memberservice.application.port.out;


public interface VerifyEmailCertPort {
    public Boolean verifyEmailCert(String email, String uuid);
}
