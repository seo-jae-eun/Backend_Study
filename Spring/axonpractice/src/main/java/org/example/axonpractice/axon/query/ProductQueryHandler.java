package org.example.axonpractice.axon.query;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.example.axonpractice.product.Product;
import org.example.axonpractice.product.ProductRepository;
import org.example.axonpractice.product.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductQueryHandler {
    private final ProductRepository productRepository;

    @QueryHandler(queryName = "list")
    private List<ProductDto> getProducts(String dummy) {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p : products) {
            ProductDto dto = ProductDto.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .price(p.getPrice())
                    .amount(p.getAmount())
                    .build();
            productDtos.add(dto);
        }
        return productDtos;
    }


    @QueryHandler
    private ProductDto getProduct(GetProductQuery query) {
        Optional<Product> result = productRepository.findById(query.getId());
        if(result.isPresent()) {
            Product product = result.get();
            ProductDto dto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .amount(product.getAmount())
                    .build();
            return dto;
        }
        return null;
    }
}
