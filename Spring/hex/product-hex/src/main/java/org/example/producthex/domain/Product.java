package org.example.producthex.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Product {
    private Long id;
    private String name;
    private String description;
    private List<String> imageFilePaths;
}
