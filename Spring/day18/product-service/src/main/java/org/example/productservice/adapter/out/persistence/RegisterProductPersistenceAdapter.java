package org.example.productservice.adapter.out.persistence;


import lombok.RequiredArgsConstructor;
import org.example.productservice.application.port.out.RegisterProductPort;
import org.example.productservice.common.PersistenceAdapter;
import org.example.productservice.domain.Product;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisterProductPersistenceAdapter implements RegisterProductPort {
    private final JpaProductRepository jpaProductRepository;

    @Override
    public Product createProduct(Product product) {
        ProductEntity productEntity = ProductEntity.builder()
                .name(product.getName())
                .price(product.getPrice())
                .build();
        productEntity = jpaProductRepository.save(productEntity);
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .build();
    }

}
