package org.example.day17.adapter.in.web;


import lombok.RequiredArgsConstructor;
import org.example.day17.application.port.in.SignupMemberCommand;
import org.example.day17.application.port.in.SignupMemberUseCase;
import org.example.day17.common.WebAdapter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@WebAdapter
@RequiredArgsConstructor
public class SignupMemberController {
    private final SignupMemberUseCase signupMemberUseCase;

    @RequestMapping(method = RequestMethod.POST, value = "/member/signup")
    void signup(@RequestPart MultipartFile image, @RequestPart SignupMemberRequest request) {
        SignupMemberCommand command = SignupMemberCommand.builder()
                .email(request.getEmail())
                .name(request.getName())
                .imageFile(image)
                .build();
        signupMemberUseCase.signupMember(command);
    }
}
