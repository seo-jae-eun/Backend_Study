package org.example.productreviewservice.adapter.out.persistence;


import lombok.RequiredArgsConstructor;
import org.example.productreviewservice.application.port.out.RegisterProductReviewPort;
import org.example.productreviewservice.common.PersistenceAdapter;
import org.example.productreviewservice.domain.ProductReview;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisterProductReviewPersistenceAdapter implements RegisterProductReviewPort {
    private final JpaProductReviewRepository jpaProductReviewRepository;

    @Override
    public ProductReview createProductReview(ProductReview product) {
        ProductReviewEntity productEntity = ProductReviewEntity.builder()
                .name(product.getName())
                .price(product.getPrice())
                .build();
        productEntity = jpaProductReviewRepository.save(productEntity);
        return ProductReview.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .build();
    }

}
