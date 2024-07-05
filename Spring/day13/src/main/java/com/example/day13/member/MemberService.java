package com.example.day13.member;

import com.example.day13.member.model.Member;
import com.example.day13.member.model.request.CreateMemberReq;
import com.example.day13.member.model.response.CreateMemberRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public CreateMemberRes create(CreateMemberReq request) {
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

    public Member getMemberByEmail(String email) {
        Optional<Member> result = memberRepository.findByEmail(email);
        if(result.isPresent()) {
            Member member = result.get();
            return member;
        }
        return null;
    }
}
