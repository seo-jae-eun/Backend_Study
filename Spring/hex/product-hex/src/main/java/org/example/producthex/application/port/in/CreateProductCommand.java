package org.example.producthex.application.port.in;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Getter
public class CreateProductCommand {
    private String name;
    private String description;
    private List<MultipartFile> imageFiles;
}
