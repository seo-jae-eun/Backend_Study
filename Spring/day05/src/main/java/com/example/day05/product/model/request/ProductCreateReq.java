package com.example.day05.product.model.request;

import com.example.day05.product.model.Product;
import com.example.day05.seller.model.Seller;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 클래스 위나 생성자 위에 달기
public class ProductCreateReq {
    private String productName;
    private int productPrice;
    private int deliveryPrice;
    private int addDeliveryPrice;
    private int outboundDays;

    public Product toEntity(Seller seller) {
        return Product.builder()
                .productName(this.productName)
                .productPrice(this.productPrice)
                .deliveryPrice(this.deliveryPrice)
                .addDeliveryPrice(this.addDeliveryPrice)
                .outboundDays(this.outboundDays)
                .seller(seller)
                .build();
    }
}
