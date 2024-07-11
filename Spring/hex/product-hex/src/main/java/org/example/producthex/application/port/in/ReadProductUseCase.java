package org.example.producthex.application.port.in;

import org.example.producthex.adapter.in.web.response.ReadProductResponse;

public interface ReadProductUseCase {
    ReadProductResponse readProduct(ReadProductCommand command);
}
