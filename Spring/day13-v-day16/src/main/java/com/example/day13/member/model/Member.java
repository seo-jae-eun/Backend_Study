package com.example.day13.member.model;

import com.example.day13.post.model.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    List<Post> posts = new ArrayList<>();

}
