package org.example.productservice.application.port.in;


import org.example.productservice.domain.Product;

public interface RegisterProductUseCase {
    Product registerProduct(RegisterProductCommand registerProductCommand);

}
