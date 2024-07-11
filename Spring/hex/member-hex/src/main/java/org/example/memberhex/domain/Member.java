package org.example.memberhex.domain;


import lombok.Builder;
import lombok.Getter;

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
