package org.example.producthex.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.example.producthex.application.port.in.CreateProductCommand;
import org.example.producthex.application.port.in.CreateProductUseCase;
import org.example.producthex.common.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
public class CreateProductController {
    private final CreateProductUseCase createProductUseCase;

    @RequestMapping(method = RequestMethod.POST, value = "/product/create")
    void create(@RequestPart CreateProductRequest request, @RequestPart MultipartFile[] files) {
        CreateProductCommand command = CreateProductCommand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .imageFiles(List.of(files))
                .build();

        createProductUseCase.createProduct(command);
    }
}
