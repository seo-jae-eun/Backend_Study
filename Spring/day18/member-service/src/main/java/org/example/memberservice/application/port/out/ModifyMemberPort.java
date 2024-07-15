package org.example.memberservice.application.port.out;


import org.example.memberservice.domain.Member;

public interface ModifyMemberPort {
    void verifyMember(Member member);
}
