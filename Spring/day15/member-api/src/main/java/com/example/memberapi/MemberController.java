package com.example.memberapi;

import com.example.memberapi.model.request.CreateMemberReq;
import com.example.memberapi.model.response.CreateMemberRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public ResponseEntity<String> test() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();
            String hostName = localHost.getHostName();
            return ResponseEntity.ok("IP Address: " + ipAddress + ", Host Name: " + hostName);
        } catch (UnknownHostException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<CreateMemberRes> signup(@RequestBody CreateMemberReq request) {
        return ResponseEntity.ok(memberService.signup(request));
    }
}
