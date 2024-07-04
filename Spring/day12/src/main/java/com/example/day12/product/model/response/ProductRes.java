package com.example.day12.product.model.response;

import lombok.*;

@Getter
@Builder
public class ProductRes {
    private Long idx;
    private String name;
    private String description;
    private Integer price;
}
