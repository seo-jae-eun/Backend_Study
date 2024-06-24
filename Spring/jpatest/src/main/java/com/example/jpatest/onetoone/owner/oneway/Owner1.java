package com.example.jpatest.onetoone.owner.oneway;

import jakarta.persistence.*;

@Entity
public class Owner1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String str;

    @OneToOne
    @JoinColumn(name="target1_idx")
    private Target1 target1;
}
