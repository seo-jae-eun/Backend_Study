package com.example.day13.member.model;

import com.example.day13.likes.model.Likes;
import com.example.day13.post.model.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String email;

    private String password;

    @Column(nullable = false)
    private String role;
    @PrePersist
    public void prePersist() {
        if (this.role == null) {
            this.role = "ROLE_USER";
        }
    }

    @OneToMany(mappedBy = "member")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Likes> likesList = new ArrayList<>();
}
