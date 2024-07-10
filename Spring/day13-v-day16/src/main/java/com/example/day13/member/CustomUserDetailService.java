package com.example.day13.member;


import com.example.day13.member.model.CustomUserDetails;
import com.example.day13.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    
    // 그림에서 5번
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findByEmail(username);

                if(result.isPresent()) {
                    return new CustomUserDetails(result.get());
                } else {
                    return null;
                }
        // 그림에서 6번(UserDetails 객체 생성), 7번(반환)

    }
}
