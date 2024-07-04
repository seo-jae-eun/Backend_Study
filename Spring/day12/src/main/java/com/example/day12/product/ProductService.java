package com.example.day12.product;

import com.example.day12.product.model.Product;
import com.example.day12.product.model.request.ProductCreateReq;
import com.example.day12.product.model.response.ProductRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public ProductRes create(ProductCreateReq productCreateReq) {
        Product product = Product.builder()
                .name(productCreateReq.getName())
                .description(productCreateReq.getDescription())
                .price(productCreateReq.getPrice())
                .build();
//        productRepository.save(product); // 이걸로 하면 테스트 코드 통과 안됨
        Product savedProduct = productRepository.save(product);

        ProductRes response = ProductRes.builder()
                .idx(savedProduct.getIdx())
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .price(savedProduct.getPrice())
                .build();

        return response;
    }

    public ProductRes read(Long id) {
        Product product = productRepository.findById(id).get();

        ProductRes response = ProductRes.builder()
                .idx(product.getIdx())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

        return response;
    }

    public List<ProductRes> list() {
        List<Product> productList = productRepository.findAll();
        List<ProductRes> productResList = new ArrayList<>();

        for(Product product : productList) {
            ProductRes response = ProductRes.builder()
                    .idx(product.getIdx())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();

            productResList.add(response);

        }
        return productResList;
    }
}
