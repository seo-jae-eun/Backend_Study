package org.example.productreviewservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;



@Builder
@Getter
@AllArgsConstructor
public class ProductReview {
    private final Long id;
    private final String name;
    private final Integer price;
}
