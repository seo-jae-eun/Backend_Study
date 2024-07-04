package com.example.day12.product.model.request;

import lombok.*;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateReq {
    private String name;
    private String description;
    private Integer price;
}
