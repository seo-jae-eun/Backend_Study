package com.example.jpatest.onetomany.oneway;

import com.example.jpatest.onetomany.twoway.Many2;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class One1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String str;
}
