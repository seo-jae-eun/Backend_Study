package org.example.memberservice.adapter.out.http;

import lombok.RequiredArgsConstructor;
import org.example.memberservice.application.port.out.VerifyEmailCertPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerifyEmailCertAdapter implements VerifyEmailCertPort {
    private final OpenFeignVerifyEmailCertClient feignClient;

    @Override
    public Boolean verifyEmailCert(String email, String uuid) {
        return feignClient.call(email, uuid);
    }
}
