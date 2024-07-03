package com.example.day11_doc.test;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.*;

@RestController
@Tag(name="테스트 컨트롤러", description = "테스트 컨트롤러")
@RequestMapping("/test")
public class TestController {


    @Operation(summary = "args01과 args02를 입력해서 회원가입하는 API입니다.")
    @RequestMapping(method = RequestMethod.GET, value = "/ex01")
    public ResponseEntity<String> ex01(String args01, String args02) {
        System.out.println(args01);
        System.out.println(args02);
        return ResponseEntity.ok("ex01");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/ex02")
    public ResponseEntity<String> ex02(String args01, String args02) {
        System.out.println(args01);
        System.out.println(args02);
        return ResponseEntity.ok("ex02");
    }
}
