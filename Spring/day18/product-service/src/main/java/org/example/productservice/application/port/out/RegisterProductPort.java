package org.example.productservice.application.port.out;


import org.example.productservice.domain.Product;

public interface RegisterProductPort {
    Product createProduct(Product product);
}
