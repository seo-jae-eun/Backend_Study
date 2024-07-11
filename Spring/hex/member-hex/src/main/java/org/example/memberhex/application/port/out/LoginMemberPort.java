package org.example.memberhex.application.port.out;


import org.example.memberhex.domain.Member;

public interface LoginMemberPort {
    Member loginMember(Member member);
}
