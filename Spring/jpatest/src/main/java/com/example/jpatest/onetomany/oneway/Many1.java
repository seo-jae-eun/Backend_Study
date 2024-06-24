package com.example.jpatest.onetomany.oneway;

import com.example.jpatest.onetomany.twoway.One2;
import jakarta.persistence.*;

@Entity
public class Many1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String str;

    @ManyToOne
    @JoinColumn(name="one1_idx")
    private One1 one1;
}
