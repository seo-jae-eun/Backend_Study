package org.example.productreviewservice.application.port.out;


import org.example.productreviewservice.domain.ProductReview;

public interface RegisterProductReviewPort {
    ProductReview createProductReview(ProductReview product);
}
