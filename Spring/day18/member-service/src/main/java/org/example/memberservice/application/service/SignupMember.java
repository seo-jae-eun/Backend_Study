package org.example.memberservice.application.service;


import lombok.RequiredArgsConstructor;
import org.example.memberservice.application.port.in.SignupMemberCommand;
import org.example.memberservice.application.port.in.SignupMemberUseCase;
import org.example.memberservice.application.port.out.SignupMemberNotificationPort;
import org.example.memberservice.application.port.out.SignupMemberPort;
import org.example.memberservice.common.UseCase;
import org.example.memberservice.domain.Member;

@UseCase
@RequiredArgsConstructor
public class SignupMember implements SignupMemberUseCase {
    private final SignupMemberPort persistencePort;
    private final SignupMemberNotificationPort notificationPort;

    @Override
    public void signupMember(SignupMemberCommand command) {
        Member member = Member.builder()
                .email(command.getEmail())
                .name(command.getName())
                .build();
        // DB쪽 출력 포트로 데이터 보내기
        persistencePort.saveMember(member);

        notificationPort.sendSignupMessage(command.getEmail());
    }
}
