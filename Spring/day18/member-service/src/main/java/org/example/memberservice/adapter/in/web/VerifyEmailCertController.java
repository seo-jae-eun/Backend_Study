package org.example.memberservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.example.memberservice.application.port.in.VerifyEmailCertCommand;
import org.example.memberservice.application.port.in.VerifyEmailCertUseCase;
import org.example.memberservice.common.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequiredArgsConstructor
public class VerifyEmailCertController {
    private final VerifyEmailCertUseCase verifyEmailCertUseCase;

    @RequestMapping("/member/verify")
    ResponseEntity verifyEmailCert(VerifyEmailCertRequest request) {
        VerifyEmailCertCommand command = VerifyEmailCertCommand.builder()
                .email(request.getEmail())
                .uuid(request.getUuid())
                .build();

        return ResponseEntity.ok().body(verifyEmailCertUseCase.verifyEmailCert(command));
    }
}
