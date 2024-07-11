package org.example.day17.domain;


import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

// 엔티티
@Builder
@Getter
public class Member {
    private Long idx;
    private String email;
    private String name;
    private String imageFilePath;
    private String emailUuid;
}
