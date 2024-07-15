package org.example.productservice.adapter.in.web;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterProductRequest {
    private String name;
    private Integer price;
}
