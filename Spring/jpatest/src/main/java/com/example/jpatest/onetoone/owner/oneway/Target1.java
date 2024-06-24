package com.example.jpatest.onetoone.owner.oneway;

import jakarta.persistence.*;

@Entity
public class Target1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String str;
}
