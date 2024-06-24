package com.example.jpatest.onetoone.owner.twoway;

import jakarta.persistence.*;

@Entity
public class Target2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String str;

    @OneToOne(mappedBy = "target2")
    private Owner2 owner2;
}
