package org.example.memberservice.application.service;


import lombok.RequiredArgsConstructor;
import org.example.memberservice.application.port.in.VerifyEmailCertCommand;
import org.example.memberservice.application.port.in.VerifyEmailCertUseCase;
import org.example.memberservice.application.port.out.ModifyMemberPort;
import org.example.memberservice.application.port.out.VerifyEmailCertPort;
import org.example.memberservice.common.UseCase;
import org.example.memberservice.domain.Member;

@UseCase
@RequiredArgsConstructor
public class VerifyEmailCert implements VerifyEmailCertUseCase {
    private final VerifyEmailCertPort verifyEmailCertPort;
    private final ModifyMemberPort modifyMemberPort;

    @Override
    public Boolean verifyEmailCert(VerifyEmailCertCommand command) {
        Boolean result = verifyEmailCertPort.verifyEmailCert(command.getEmail(), command.getUuid());

        if (result) {
            modifyMemberPort.verifyMember(Member.builder()
                    .email(command.getEmail())
                    .enabled(true)
                    .build());

            return true;
        }
        return false;
    }
}
