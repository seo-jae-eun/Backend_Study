package com.example.day09.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String name;

    @ColumnDefault("NULL")
    private LocalDateTime updatedAt;

    @ColumnDefault("NULL")
    private Integer playTime;

    @ColumnDefault("NULL")
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @ColumnDefault("NULL")
    private Section section;
}