package com.example.day01.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

// 스프링이 처음 실행될 때 component-scan 작업을 할 때 아래 어노테이션이 달려있는 클래스들은
// 자동으로 객체를 생성(싱글톤)해서 스프링 컨테이너에서 관리
// 스프링에서는 component-scan 할 경로를 개발자가 직접 설정
// 스프링 부트에서는 component-scan 할 경로가 main 메소드가 있는 클래스의 패키지로 기본 설정
// @Controller, @RestController, @Service, @Repository, @Configuration <- @Component (가 달려있는 애들임)



// @Controller는 ViewResolver를 사용, resources/templates/ 에 html 파일이 필요, thymeleaf 필요
// @RestController는 ViewResolver를 사용 x

// -> 간단하게 말하면
// @Controller는 html 페이지 반환
// @RestController는 그냥 글자 반환

// 어노테이션만 달아놓으면 Controller의 기능을 받아 쓸 수 있음
// HTTP 요청을 받아서 HTTP 응답을 보내주는 역할
@Controller
//@RestController -> 이것만 사용할거임
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping(method = RequestMethod.GET, value = "/ex01")
    public String ex01(Model model) {
        model.addAttribute("message", "ㅎ_ㅎ");
        return "test";
    }
}
