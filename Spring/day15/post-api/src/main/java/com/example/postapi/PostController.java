package com.example.postapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/post")
public class PostController {

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public ResponseEntity<String> test(@RequestHeader("X-User-Idx") Long userIdx,
                                       @RequestHeader("X-User-Email") String email,
                                       @RequestHeader("X-User-Role") String role,
                                       Long idx) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();
            String hostName = localHost.getHostName();
            return ResponseEntity.ok("회원 idx : " + userIdx + ", 회원 email : " + email + ", 회원 role : " + role + "\n게시글 번호 : " + idx + "\nIP Address: " + ipAddress + ", Host Name: " + hostName);
        } catch (UnknownHostException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
