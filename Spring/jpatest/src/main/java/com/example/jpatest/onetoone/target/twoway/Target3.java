package com.example.jpatest.onetoone.target.twoway;

import jakarta.persistence.*;

@Entity
public class Target3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String str;

    @OneToOne
    @JoinColumn(name="owner3_idx")
    private Owner3 owner3;
}
