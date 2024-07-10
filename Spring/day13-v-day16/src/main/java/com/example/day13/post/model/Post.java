package com.example.day13.post.model;

import com.example.day13.likes.Likes;
import com.example.day13.member.model.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String contents;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_idx")
    private Member member;

    @ColumnDefault(value = "0")
    private Integer likesCount;
//
//    @Version    // 낙관적 락 : 프로그램 단에서 사용하는 Lock, 낙관적 락 테스트할 때는 비관적 락 주석 처리
//    @ColumnDefault(value = "0")
//    private Integer version;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    List<Likes> likes = new ArrayList<>();

    public void addLikesCount() {
        this.likesCount = this.likesCount + 1;
    }

    public void subLikesCount() {
        this.likesCount = this.likesCount - 1;
    }

}
