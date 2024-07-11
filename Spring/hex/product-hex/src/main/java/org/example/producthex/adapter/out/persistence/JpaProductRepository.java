package org.example.producthex.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT p FROM ProductEntity p JOIN FETCH p.productImages pi WHERE p.id = :id")
    Optional<ProductEntity> findByIdWithproductImage(Long id);
}
