package com.example.day13_tdd.post.model;

import com.example.day13_tdd.likes.Likes;
import com.example.day13_tdd.member.model.Member;
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
@Builder
@DynamicInsert // 이걸 달아줘야 입력을 안한 값은 빼고 저장함
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String contents;

    @ColumnDefault(value = "0")
//    @Version // 낙관적 락 : 프로그램 단에서 사용하는 Lock <-> 비관적 락 : DB 단에서 사용하는 Lock
    private Integer likesCount;
    // update post set ... and version = 1;

    @Version
    @ColumnDefault(value = "0")
    private Integer version;


    @ManyToOne
    @JoinColumn(name="member_idx")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Likes> likes = new ArrayList<>();


    public void addLikesCount() {
        this.likesCount = this.likesCount + 1;
    }
    public void subLikesCount() {
        this.likesCount = this.likesCount - 1;
    }

}
