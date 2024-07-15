package org.example.emailcertservice.adapter.in.web;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VerifyEmailCertRequest {
    private String email;
    private String uuid;
}
