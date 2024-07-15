package org.example.memberservice.application.port.in;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyEmailCertCommand {
    private String email;
    private String uuid;
}
