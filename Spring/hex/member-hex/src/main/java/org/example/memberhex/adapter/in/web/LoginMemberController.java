package org.example.memberhex.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.example.memberhex.application.port.in.LoginMemberCommand;
import org.example.memberhex.application.port.in.LoginMemberUseCase;
import org.example.memberhex.common.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@WebAdapter
@RequiredArgsConstructor
public class LoginMemberController {
    private final LoginMemberUseCase loginMemberUseCase;

    @RequestMapping(method = RequestMethod.POST, value = "/member/login")
    ResponseEntity<String> loginMember(@RequestBody LoginMemberRequest request) {
        LoginMemberCommand command = LoginMemberCommand.builder()
                .email(request.getEmail())
                .build();

        if(loginMemberUseCase.loginMember(command)) {
            return ResponseEntity.ok("로그인 성공");
        }

        return ResponseEntity.notFound().build();
        
    }
}
