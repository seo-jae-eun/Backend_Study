package org.example.productreviewservice.application.service;


import lombok.RequiredArgsConstructor;
import org.example.productreviewservice.application.port.in.RegisterProductReviewCommand;
import org.example.productreviewservice.application.port.in.RegisterProductReviewUseCase;
import org.example.productreviewservice.application.port.out.RegisterProductReviewPort;
import org.example.productreviewservice.common.UseCase;
import org.example.productreviewservice.domain.ProductReview;

@UseCase
@RequiredArgsConstructor
public class ProductReviewService implements RegisterProductReviewUseCase {
    private final RegisterProductReviewPort registerProductReviewPort;

    @Override
    public ProductReview registerProductReview(RegisterProductReviewCommand command) {

        ProductReview product = ProductReview.builder()
                .name(command.getName())
                .price(command.getPrice())
                .build();

        product = registerProductReviewPort.createProductReview(product);

        return product;
    }

}
