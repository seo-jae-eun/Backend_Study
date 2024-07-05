package com.example.day13_tdd.member.model;

import com.example.day13_tdd.post.model.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Member  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String email;

    private String password;

    private String role;

    private Boolean enabled;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

}
