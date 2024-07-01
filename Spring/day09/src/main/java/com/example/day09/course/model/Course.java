package com.example.day09.course.model;

import com.example.day09.course.model.response.PostCourseRes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ColumnDefault("NULL")
    private LocalDateTime updatedAt;

    @ColumnDefault("NULL")
    private String description;

    @ColumnDefault("NULL")
    private String image;

    @ColumnDefault("1")
    private boolean isDisplay;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "int(11) DEFAULT NULL CHECK (price >= 0)")
    private int price;

    @Builder.Default
    @OneToMany(mappedBy = "course")
    private List<Section> sectionList = new ArrayList<>();


    public PostCourseRes toDto() {
        return PostCourseRes.builder()
                .id(this.id)
                .name(this.name)
                .image(this.image)
                .description(this.description)
                .price(this.price)
                .build();

    }

}

