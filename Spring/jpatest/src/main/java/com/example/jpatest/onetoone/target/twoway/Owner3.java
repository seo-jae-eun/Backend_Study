package com.example.jpatest.onetoone.target.twoway;

import jakarta.persistence.*;

@Entity
public class Owner3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String str;

    @OneToOne(mappedBy = "owner3")
    private Target3 target3;
}
