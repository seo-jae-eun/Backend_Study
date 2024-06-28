package com.example.day08.member.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String email;
    private String password;
    private String role;

    private Boolean active;

    public void changeActive(Boolean active) {
        if (active == null) {
            throw new IllegalArgumentException("active cannot be null");
        }
        this.active = active;

    }
}
