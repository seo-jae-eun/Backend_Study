package org.example.producthex.application.service;

import lombok.RequiredArgsConstructor;
import org.example.producthex.adapter.in.web.response.ReadProductResponse;
import org.example.producthex.application.port.in.ReadProductCommand;
import org.example.producthex.application.port.in.ReadProductUseCase;
import org.example.producthex.application.port.out.ReadProductPort;
import org.example.producthex.common.UseCase;
import org.example.producthex.domain.Product;


@UseCase
@RequiredArgsConstructor
public class ReadProduct implements ReadProductUseCase {
    private final ReadProductPort persistencePort;

    @Override
    public ReadProductResponse readProduct(ReadProductCommand command) {
        Product product = Product.builder()
                .id(command.getId())
                .build();

        product = persistencePort.readProduct(product);
        return ReadProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .productImages(product.getImageFilePaths())
                .build();
    }
}
