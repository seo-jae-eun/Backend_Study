package org.example.emailcertservice.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmailCert {
    private Long idx;
    private String email;
    private String uuid;
}
