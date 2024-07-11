package org.example.day17.application.port.out;


import org.example.day17.domain.Member;

public interface LoginMemberPort {
    Member loginMember(Member member);
}
