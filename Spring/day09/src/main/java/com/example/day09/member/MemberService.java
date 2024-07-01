package com.example.day09.member;

import com.example.day09.common.BaseResponse;
import com.example.day09.member.model.Member;
import com.example.day09.member.model.response.PostSignupRes;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final JavaMailSender emailSender;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public BaseResponse<PostSignupRes> signup(String email, String password, String name) {

        Member member = Member.builder()
                .createdAt(LocalDateTime.now())
                .email(email)
                .enabled(false)
                .name(name)
                .password(bCryptPasswordEncoder.encode(password))
                .build();
        memberRepository.save(member);

        BaseResponse<PostSignupRes> response = new BaseResponse<>(new PostSignupRes(member.getId(), member.getEmail(), member.getName()));
        return response;
    }


    public Member getMemberByEmail(String email) {
        Optional<Member> result = memberRepository.findByEmail(email);
        if(result.isPresent()) {
            Member member = result.get();
            return member;
        }
        return null;
    }

    public String sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[Pochacco] Welcome to join !!");
        String uuid = UUID.randomUUID().toString();
        message.setText("http://localhost/api/member/verify?email="+email+"&uuid="+uuid);

        emailSender.send(message);

        return uuid;
    }

    public void enabledMember(String email) {
        Optional<Member> result = memberRepository.findByEmail(email);
        if(result.isPresent()) {
            Member member = result.get();
            member.changeEnabled(true);
            memberRepository.save(member);
        }
    }
}
