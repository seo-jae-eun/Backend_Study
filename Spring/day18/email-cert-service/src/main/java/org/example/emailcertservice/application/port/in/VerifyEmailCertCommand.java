package org.example.emailcertservice.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VerifyEmailCertCommand {
    private String email;
    private String uuid;
}
