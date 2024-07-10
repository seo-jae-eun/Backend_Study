package com.example.day13.member;

import com.example.day13.member.model.Member;
import com.example.day13.member.model.request.PostSignupReq;
import com.example.day13.member.model.response.PostSignupRes;
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
                .idx(member.getIdx())
                .email(member.getEmail())
                .build();
    }

}
