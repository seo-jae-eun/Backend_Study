package org.example.memberservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Member {
    private Long idx;
    private String email;
    private String name;
    private String imageFilePath;
    private String emailUuid;
    private Boolean enabled;
}
