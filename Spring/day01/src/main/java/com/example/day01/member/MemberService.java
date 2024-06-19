package com.example.day01.member;

import com.example.day01.member.model.MemberLoginReq;
import com.example.day01.member.model.MemberSignupReq;
import com.example.day01.member.model.MemberSignupRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String method01() {
        System.out.println("메소드 실행됨");
        return "Member Service Method01";
    }

    public MemberSignupRes signup(MemberSignupReq memberSignupReq) {
        String emailResult = memberSignupReq.getEmail()+"처리함";
        String nameResult = memberSignupReq.getName()+"처리함";

        return new MemberSignupRes(emailResult, nameResult);
    }


    // 요청 모델을 받아서 글자를 반환하는 회원 로그인 메소드
    public String login(MemberLoginReq memberLoginReq) {
        // 받은 이메일이 test01이고 패스워드가 qwer1234이면 '성공'이라는 글자를 반환
        // 아니면 '실패'라는 글자를 반환
        String result;
        if(memberLoginReq.getEmail().equals("test01") && memberLoginReq.getPassword().equals("qwer1234")) {
            result = "성공";
        }
        else {
            result = "실패";
        }
        return result;
    }
}
