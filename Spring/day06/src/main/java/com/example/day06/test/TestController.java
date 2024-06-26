package com.example.day06.test;

import com.example.day06.member.model.CustomUserDetails;
import com.example.day06.member.model.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;

@RestController
@RequestMapping("/test")

public class TestController {
    @RequestMapping(method = RequestMethod.GET, value = "/ex01")
    public ResponseEntity<String> ex01(@AuthenticationPrincipal CustomUserDetails user) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
//        System.out.println(user.getUsername());
        System.out.println(user.getUsername());
        return ResponseEntity.ok("접근 가능");
    }

}
