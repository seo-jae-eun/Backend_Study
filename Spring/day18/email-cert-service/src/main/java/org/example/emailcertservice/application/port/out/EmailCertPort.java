package org.example.emailcertservice.application.port.out;

import org.example.emailcertservice.domain.EmailCert;

public interface EmailCertPort {
    void saveEmailCert(EmailCert emailCert);
    Boolean findEmailCert(EmailCert emailCert);
}
