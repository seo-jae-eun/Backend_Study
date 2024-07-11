package org.example.day17.application.service;


import lombok.RequiredArgsConstructor;
import org.example.day17.application.port.in.SignupMemberCommand;
import org.example.day17.application.port.in.SignupMemberUseCase;
import org.example.day17.application.port.out.S3UploadPort;
import org.example.day17.application.port.out.SignupMemberEmailCertificatePort;
import org.example.day17.application.port.out.SignupMemberNotificationPort;
import org.example.day17.application.port.out.SignupMemberPort;
import org.example.day17.common.UseCase;
import org.example.day17.domain.Member;

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
