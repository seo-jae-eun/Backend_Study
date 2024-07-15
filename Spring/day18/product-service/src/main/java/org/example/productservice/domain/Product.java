package org.example.productservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;



@Builder
@Getter
@AllArgsConstructor
public class Product {
    private final Long id;
    private final String name;
    private final Integer price;
}
