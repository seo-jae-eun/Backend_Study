package com.example.day13_tdd.member;

import com.example.day13_tdd.member.model.Member;
import com.example.day13_tdd.member.model.request.PostSignupReq;
import com.example.day13_tdd.member.model.response.PostSignupRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public PostSignupRes signup(PostSignupReq request) {
        Member member = Member.builder()
                .email(request.getEmail())
                .role("ROLE_USER")
                .enabled(true)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();



        member = memberRepository.save(member);

        return PostSignupRes.builder()
                .id(member.getIdx())
                .email(member.getEmail())
                .build();
    }

}
