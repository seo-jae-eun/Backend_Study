package org.example.memberservice.adapter.out.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "EmailCert", url = "http://localhost:8082/emailcert/verify")
public interface OpenFeignVerifyEmailCertClient {
    @GetMapping
    Boolean call(@RequestParam String email, @RequestParam String uuid);
}
