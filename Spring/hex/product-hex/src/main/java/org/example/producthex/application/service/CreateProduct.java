package org.example.producthex.application.service;

import lombok.RequiredArgsConstructor;
import org.example.producthex.application.port.in.CreateProductCommand;
import org.example.producthex.application.port.in.CreateProductUseCase;
import org.example.producthex.application.port.out.CreateProductPort;
import org.example.producthex.application.port.out.S3UploadPort;
import org.example.producthex.common.UseCase;
import org.example.producthex.domain.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateProduct implements CreateProductUseCase {
    private final CreateProductPort persistencePort;
    private final S3UploadPort s3UploadPort;
    @Override
    public void createProduct(CreateProductCommand command) {
        // S3 저장
        List<String> imageFilePaths = new ArrayList<>();
        for(MultipartFile file : command.getImageFiles()) {
            imageFilePaths.add(s3UploadPort.uploadMemberProfileImage(file));
        }

        Product product = Product.builder()
                .name(command.getName())
                .description(command.getDescription())
                .imageFilePaths(imageFilePaths)
                .build();

        // DB 저장
        persistencePort.createProduct(product);
    }
}
