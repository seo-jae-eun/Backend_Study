package org.example.emailcertservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.example.emailcertservice.application.port.in.VerifyEmailCertCommand;
import org.example.emailcertservice.application.port.in.VerifyEmailCertUseCase;
import org.example.emailcertservice.common.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@WebAdapter
@RequiredArgsConstructor
public class VerifyEmailCertController {
    private final VerifyEmailCertUseCase verifyEmailCertUseCase;

    @RequestMapping(method = RequestMethod.GET, value = "/emailcert/verify")
    ResponseEntity<Boolean> verifyEmailCert(VerifyEmailCertRequest request) {
        VerifyEmailCertCommand command = VerifyEmailCertCommand.builder()
                .email(request.getEmail())
                .uuid((request.getUuid()))
                .build();

        return ResponseEntity.ok(verifyEmailCertUseCase.verifyEmailCert(command));
    }

}
