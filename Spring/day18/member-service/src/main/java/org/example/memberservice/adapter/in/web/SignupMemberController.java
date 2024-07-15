package org.example.memberservice.adapter.in.web;


import lombok.RequiredArgsConstructor;
import org.example.memberservice.application.port.in.SignupMemberCommand;
import org.example.memberservice.application.port.in.SignupMemberUseCase;
import org.example.memberservice.common.WebAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@WebAdapter
@RequiredArgsConstructor
public class SignupMemberController {
    private final SignupMemberUseCase signupMemberUseCase;

    @RequestMapping(method = RequestMethod.POST, value = "/member/signup")
    void signup(@RequestBody SignupMemberRequest request) {
        SignupMemberCommand command = SignupMemberCommand.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();
        signupMemberUseCase.signupMember(command);
    }
}
