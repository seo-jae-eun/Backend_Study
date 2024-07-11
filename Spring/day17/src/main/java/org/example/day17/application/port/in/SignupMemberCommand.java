package org.example.day17.application.port.in;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
@Builder
public class SignupMemberCommand {
    private String email;
    private String name;
    private MultipartFile imageFile;
}
