package org.example.productservice.application.service;

import lombok.RequiredArgsConstructor;
import org.example.productservice.application.port.in.RegisterProductCommand;
import org.example.productservice.application.port.in.RegisterProductUseCase;
import org.example.productservice.application.port.out.RegisterProductPort;
import org.example.productservice.common.UseCase;
import org.example.productservice.domain.Product;

@UseCase
@RequiredArgsConstructor
public class ProductService implements RegisterProductUseCase {
    private final RegisterProductPort registerProductPort;

    @Override
    public Product registerProduct(RegisterProductCommand command) {

        Product product = Product.builder()
                .name(command.getName())
                .price(command.getPrice())
                .build();

        product = registerProductPort.createProduct(product);

        return product;
    }

}
