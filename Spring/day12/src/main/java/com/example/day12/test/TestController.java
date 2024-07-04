package com.example.day12.test;

import com.example.day12.common.annotation.Timer;
import com.example.day12.test.model.RequestDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/test")
//@Slf4j // lombok
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class); // 수동
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @Timer
    @RequestMapping(method = RequestMethod.GET, value = "/ex01")
    public ResponseEntity<String> ex01(@Valid @RequestBody RequestDTO request) {
        // 스프링 기능을 사용하지 않고 데이터 검증 -> 이렇게 하면 컨트롤러에 if 문이 많아짐 -> Spring Validation
        // Valid (java), Validated (Spring)
//        if(request.getEmail() == null) {
//            return ResponseEntity.badRequest().body("이메일을 입력해주세요.");
//        } else if(!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", request.getEmail())) { // 정규 표현식에 일치하면 true
//            return ResponseEntity.badRequest().body("이메일 형식에 맞게 입력해주세요.");
//        }
        // 로그 레벨 (critical, alert), error (심각)|(경고)  warn, info, debug, trace
        logger.info("SUCCESS:[GET][200][{}, {}, {}]", request.getEmail(), request.getPassword(), request.getName());
//        logger.info(request.getEmail());
//        System.out.println(request.getEmail());
//        System.out.println(request.getPassword());
//        System.out.println(request.getName());


        testService.ex01();



        return ResponseEntity.ok("ex01");
    }

}
