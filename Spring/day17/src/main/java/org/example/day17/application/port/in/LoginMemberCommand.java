package org.example.day17.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginMemberCommand {
    private String email;
}
