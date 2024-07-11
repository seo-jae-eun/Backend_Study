package org.example.producthex.application.port.out;

import org.example.producthex.domain.Product;

public interface CreateProductPort {
    void createProduct(Product product);
}
