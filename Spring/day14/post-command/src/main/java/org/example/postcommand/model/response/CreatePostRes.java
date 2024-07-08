package org.example.postcommand.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreatePostRes {
    private Long idx;
    private String contents;
}
