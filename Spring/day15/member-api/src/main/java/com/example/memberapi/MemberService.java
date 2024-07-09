package com.example.memberapi;

import com.example.memberapi.model.Member;
import com.example.memberapi.model.request.CreateMemberReq;
import com.example.memberapi.model.response.CreateMemberRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CreateMemberRes signup(CreateMemberReq request) {
        Member member = Member.builder()
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build();

        Member savedMember = memberRepository.save(member);

        return CreateMemberRes.builder()
                .idx(savedMember.getIdx())
                .email(savedMember.getEmail())
                .build();
    }
}
