package com.example.day05.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="product_id") // 관계에서 주인이 되는 쪽에 추가, 외래키를 관리하는 쪽이 주인
    private Product product;
}
