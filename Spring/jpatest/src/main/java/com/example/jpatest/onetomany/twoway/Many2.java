package com.example.jpatest.onetomany.twoway;

import jakarta.persistence.*;

@Entity
public class Many2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String str;

    @ManyToOne
    @JoinColumn(name="one2_idx")
    private One2 one2;
}
