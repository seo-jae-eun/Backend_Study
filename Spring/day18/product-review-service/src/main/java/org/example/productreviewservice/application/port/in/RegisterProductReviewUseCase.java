package org.example.productreviewservice.application.port.in;


import org.example.productreviewservice.domain.ProductReview;

public interface RegisterProductReviewUseCase {
    ProductReview registerProductReview(RegisterProductReviewCommand registerProductReviewCommand);

}
