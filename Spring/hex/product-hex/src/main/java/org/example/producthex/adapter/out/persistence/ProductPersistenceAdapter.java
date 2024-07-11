package org.example.producthex.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.example.producthex.application.port.out.CreateProductPort;
import org.example.producthex.application.port.out.ReadProductPort;
import org.example.producthex.common.PersistenceAdapter;
import org.example.producthex.domain.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements CreateProductPort, ReadProductPort {
    private final JpaProductRepository jpaProductRepository;
    private final JpaProductImageRepository jpaProductImageRepository;

    @Override
    public void createProduct(Product product) {
        ProductEntity productEntity = ProductEntity.builder()
                .name(product.getName())
                .description(product.getDescription())
                .build();

        jpaProductRepository.save(productEntity);

        for(String path : product.getImageFilePaths()) {
            ProductImageEntity productImageEntity = ProductImageEntity.builder()
                    .path(path)
                    .product(productEntity)
                    .build();
            jpaProductImageRepository.save(productImageEntity);
        }

    }

    @Override
    public Product readProduct(Product product) {
        Optional<ProductEntity> result = jpaProductRepository.findByIdWithproductImage(product.getId());
        if(result.isPresent()) {
            ProductEntity productEntity = result.get();
            return Product.builder()
                    .id(productEntity.getId())
                    .name(productEntity.getName())
                    .description(productEntity.getDescription())
                    .imageFilePaths(productEntity.getProductImages().stream().map(ProductImageEntity::getPath).toList())
                    .build();
        }
        return null;
    }
}
