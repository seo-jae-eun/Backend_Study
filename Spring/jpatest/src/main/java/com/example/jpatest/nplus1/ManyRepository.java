package com.example.jpatest.nplus1;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManyRepository extends JpaRepository<Many, Long> {
}
