package com.example.day08.member;

import com.example.day08.member.model.EmailVerify;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.index.qual.SearchIndexBottom;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailVerifyService {
    private final EmailVerifyRepository emailVerifyRepository;

    public Boolean isExist(String email, String uuid) {
        Optional<EmailVerify> result = emailVerifyRepository.findByEmail(email);
        if(result.isPresent()) {
            EmailVerify emailVerify = result.get();
            if(emailVerify.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public void save(String email, String uuid) {
        EmailVerify emailVerify = EmailVerify.builder()
                .email(email)
                .uuid(uuid)
                .build();

        emailVerifyRepository.save(emailVerify);
    }

}
