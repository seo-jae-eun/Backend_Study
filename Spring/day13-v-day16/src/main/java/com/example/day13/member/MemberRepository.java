package com.example.day13.member;


import com.example.day13.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    public Optional<Member> findByEmail(String email);

}
