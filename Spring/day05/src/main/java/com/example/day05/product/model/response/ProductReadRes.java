package com.example.day05.product.model.response;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReadRes {
    private Long id;
    private String productName;
    private int productPrice;
    private int deliveryPrice;
    private int addDeliveryPrice;
    private int outboundDays;
    private String sellerName;
    private String imageUrl;
}
