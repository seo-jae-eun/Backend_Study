package org.example.producthex.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.example.producthex.application.port.in.ReadProductCommand;
import org.example.producthex.application.port.in.ReadProductUseCase;
import org.example.producthex.common.WebAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@WebAdapter
@RequiredArgsConstructor
public class ReadProductController {
    private final ReadProductUseCase readProductUseCase;

    @RequestMapping(method = RequestMethod.GET, value = "/product/read")
    void read(Long id) {
        ReadProductCommand command = ReadProductCommand.builder()
                .id(id)
                .build();
        readProductUseCase.readProduct(command);
    }
}
