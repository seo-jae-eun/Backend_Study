package org.example.memberhex.application.service;


import lombok.RequiredArgsConstructor;
import org.example.memberhex.application.port.in.SignupMemberCommand;
import org.example.memberhex.application.port.in.SignupMemberUseCase;
import org.example.memberhex.application.port.out.S3UploadPort;
import org.example.memberhex.application.port.out.SignupMemberEmailCertificatePort;
import org.example.memberhex.application.port.out.SignupMemberNotificationPort;
import org.example.memberhex.application.port.out.SignupMemberPort;
import org.example.memberhex.common.UseCase;
import org.example.memberhex.domain.Member;

@UseCase
@RequiredArgsConstructor
public class SignupMember implements SignupMemberUseCase {
    private final SignupMemberPort persistencePort;
    private final SignupMemberNotificationPort kafkaPort;
    private final S3UploadPort s3UploadPort;
    private final SignupMemberEmailCertificatePort emailCertificatePort;
    @Override
    public void signupMember(SignupMemberCommand command) {
        // email 쪽 출력 포트로 메일주소 보내기
        String emailUuid = emailCertificatePort.sendEmail(command.getEmail());
        // AWS S3에 이미지 저장
        String imageFilePath = s3UploadPort.uploadMemberProfileImage(command.getImageFile());
        Member member = Member.builder()
                .email(command.getEmail())
                .name(command.getName())
                .emailUuid(emailUuid)
                .imageFilePath(imageFilePath)
                .build();
        // 출력 포트로 데이터 보내기
        persistencePort.saveMember(member);

        // 카프카로 메시지 보내기
        kafkaPort.sendSignupMessage("가입했어요.");
    }
}
