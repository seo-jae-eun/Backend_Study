package org.example.day17.application.service;

import lombok.RequiredArgsConstructor;
import org.example.day17.application.port.in.LoginMemberCommand;
import org.example.day17.application.port.in.LoginMemberUseCase;
import org.example.day17.application.port.out.LoginMemberPort;
import org.example.day17.common.UseCase;
import org.example.day17.domain.Member;

@UseCase
@RequiredArgsConstructor
public class LoginMember implements LoginMemberUseCase {
    private final LoginMemberPort port;
    @Override
    public boolean loginMember(LoginMemberCommand command) {
        Member member = Member.builder()
                .email(command.getEmail())
                .build();
        member = port.loginMember(member);

        if(member != null) {
            return true;
        }

        return false;

    }
}
