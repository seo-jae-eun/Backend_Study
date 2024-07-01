package com.example.day09.member.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String role;
    @PrePersist
    public void prePersist() {
        if (this.role == null) {
            this.role = "ROLE_USER";
        }
    }

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ColumnDefault("NULL")
    private LocalDateTime updatedAt;

    @ColumnDefault("0")
    private Boolean enabled;

    @Column(unique = true)
    @ColumnDefault("NULL")
    private String profileImage;


    public void changeEnabled(Boolean enabled) {
        if (enabled == null) {
            throw new IllegalArgumentException("active cannot be null");
        }
        this.enabled = enabled;

    }
}
