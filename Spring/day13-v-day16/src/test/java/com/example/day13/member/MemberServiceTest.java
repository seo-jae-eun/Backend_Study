package com.example.day13.member;

import com.example.day13.likes.Likes;
import com.example.day13.likes.LikesService;
import com.example.day13.member.model.Member;
import com.example.day13.member.model.request.PostSignupReq;
import com.example.day13.member.model.response.PostSignupRes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private MemberService memberService;

    @Test
    void memberService_signup_success() {
        Member member = Member.builder()
                .idx(1L)
                .email("test01@test.com")
                .role("ROLE_USER")
                .password("encoded_password")
                .enabled(true)
                .build();
        given(passwordEncoder.encode(any(String.class))).willReturn("encoded_password");
        given(memberRepository.save(any(Member.class))).willReturn(member);
        PostSignupReq request = PostSignupReq.builder()
                .email("test01@test.com")
                .password("qwer1234")
                .build();

        //when
        PostSignupRes response = memberService.signup(request);

        assertNotNull(response);
        assertEquals(response.getIdx(), member.getIdx());
    }
}