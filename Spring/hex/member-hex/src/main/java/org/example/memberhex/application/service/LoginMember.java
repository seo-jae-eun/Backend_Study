package org.example.memberhex.application.service;

import lombok.RequiredArgsConstructor;
import org.example.memberhex.application.port.in.LoginMemberCommand;
import org.example.memberhex.application.port.in.LoginMemberUseCase;
import org.example.memberhex.application.port.out.LoginMemberPort;
import org.example.memberhex.common.UseCase;
import org.example.memberhex.domain.Member;

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
