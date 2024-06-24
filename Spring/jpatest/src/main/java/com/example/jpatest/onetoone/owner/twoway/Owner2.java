package com.example.jpatest.onetoone.owner.twoway;

import jakarta.persistence.*;

@Entity
public class Owner2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String str;

    @OneToOne
    @JoinColumn(name="target2_idx")
    private Target2 target2;
}
