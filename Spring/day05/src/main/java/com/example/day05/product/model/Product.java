package com.example.day05.product.model;

import com.example.day05.product.model.request.ProductCreateReq;
import com.example.day05.product.model.response.ProductCreateRes;
import com.example.day05.product.model.response.ProductReadRes;
import com.example.day05.seller.model.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Integer productPrice;
    private Integer deliveryPrice;
    private Integer addDeliveryPrice;
    private Integer outboundDays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="seller_id")
    private Seller seller;

    @OneToOne(mappedBy = "product")
    private ProductImage productImage;

    public ProductReadRes toDto() {
        return ProductReadRes.builder()
                .id(this.id)
                .productName(this.productName)
                .productPrice(this.productPrice)
                .deliveryPrice(this.deliveryPrice)
                .addDeliveryPrice(this.addDeliveryPrice)
                .outboundDays(this.outboundDays)
                .imageUrl(this.productImage.getImageUrl())
                .build();

    }
}
