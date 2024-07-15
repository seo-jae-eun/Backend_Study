package org.example.productreviewservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductReviewRepository extends JpaRepository<ProductReviewEntity,Long> {
}
