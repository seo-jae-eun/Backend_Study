package com.example.day06.member;

import com.example.day06.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signup(String username, String password) {
        Member member = Member.builder()
                .email(username)
                .password(bCryptPasswordEncoder.encode(password))
                .role("ROLE_USER")
                .build();
        memberRepository.save(member);
    }

    public void adminSignup(String username, String password) {

        Member member = Member.builder()
                .email(username)
                .password(bCryptPasswordEncoder.encode(password))
                .role("ROLE_ADMIN")
                .build();
        memberRepository.save(member);
    }
}
