package org.example.memberhex.application.port.in;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class SignupMemberCommand {
    private String email;
    private String name;
    private MultipartFile imageFile;
}
