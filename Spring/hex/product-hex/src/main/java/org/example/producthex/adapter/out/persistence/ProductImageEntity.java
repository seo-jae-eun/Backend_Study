package org.example.producthex.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_image")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_idx")
    ProductEntity product;
}
