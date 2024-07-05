package com.example.day13.likes.model;

import com.example.day13.member.model.Member;
import com.example.day13.post.model.Post;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_idx")
    private Post post;



}
