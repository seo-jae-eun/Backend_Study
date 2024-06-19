package com.example.day01.member;

import com.example.day01.member.model.MemberLoginReq;
import com.example.day01.member.model.MemberSignupReq;
import com.example.day01.member.model.MemberSignupRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Member;

@RestController
@RequestMapping(value = "/member")
public class MemberController {
    
    // 자동으로 연결해라
//    @Autowired
    private MemberService memberService;

    
    
    // 이거 만들기 귀차나 -> lombok 라이브러리 사용 -> @RequiredArgsConstructor 사용 (이것만 붙이면 이 밑에 생성자 만들어줌)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
//    -> 왜 이렇게 했어요????

    @RequestMapping(method = RequestMethod.GET, value = "/ex01")
    // 매개 변수에 DTO 넣어서 여기로 전달 받게 해놓으면 스프링이 알아서 넣어줌 (Setter가 만들어져 있어야 함)
    public String ex01(MemberSignupReq memberSignupReq) {
        // MemberService 객체를 생성하지 않았는데 사용이 가능한 것은 스프링 부트가 객체를 생성해주기 때문
//        MemberService memberService = new MemberService(); -> 이렇게 해도 되는데 여기에 들어올때마다 객체를 생성하기 때문에 Autowired 사용하는 것보다 안좋음

        System.out.println(memberSignupReq.getEmail());
        System.out.println(memberSignupReq.getPassword());
        System.out.println(memberSignupReq.getName());

        String result = memberService.method01();
        return result;
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/signup")
//    // RequestBody 붙여야 json 형태의 데이터를 받을 수 있다.
//    public String signup(@RequestBody MemberSignupReq memberSignupReq) {
//        System.out.println(memberSignupReq.getEmail());
//        System.out.println(memberSignupReq.getPassword());
//        System.out.println(memberSignupReq.getName());
//
//        String result = memberService.method01();
//        return result;
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<MemberSignupRes> signup(@RequestBody MemberSignupReq memberSignupReq) {

        MemberSignupRes memberSignupRes = memberService.signup(memberSignupReq);
        return ResponseEntity.ok(memberSignupRes);
    }



    // 4. 회원 로그인 기능
    // POST로 요청 모델을 받아서 글자를 ResponseEntity에 담아서 반환하는 회원 로그인 메소드
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<String> login(@RequestBody MemberLoginReq memberLoginReq) {
        // 서비스 회원 로그인 메소드 실행
        String memberLoginRes = memberService.login(memberLoginReq);
        // 실행 결과 반환
        return ResponseEntity.ok(memberLoginRes);
    }
}
