package com.example.day08.member;

import com.example.day08.member.model.EmailVerify;
import com.example.day08.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final JavaMailSender emailSender;
    private final MemberRepository memberRepository;

    public void signup(String username, String password) {

        Member member = Member.builder()
                .email(username)
                .password(password)
                .role("ROLE_USER")
                .active(false)
                .build();
        memberRepository.save(member);
    }


    public Member getMemberByEmail(String nickName) {
        Optional<Member> result = memberRepository.findByEmail(nickName);
        if(result.isPresent()) {
            Member member = result.get();
            return member;
        }
        return null;
    }

    public String sendEmail(String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(username);
        message.setSubject("[내 사이트] 가입 환영");
        String uuid = UUID.randomUUID().toString();
        message.setText("http://localhost/api/member/verify?email="+username+"&uuid="+uuid);

        emailSender.send(message);

        return uuid;
    }

    public void activeMember(String email) {
        Optional<Member> result = memberRepository.findByEmail(email);
        if(result.isPresent()) {
            Member member = result.get();
            member.changeActive(true);
            memberRepository.save(member);
        }
    }
}
