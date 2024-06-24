package com.example.jpatest.onetomany.twoway;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class One2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String str;

    @OneToMany(mappedBy = "one2")
    private List<Many2> many2s = new ArrayList<>();
}
