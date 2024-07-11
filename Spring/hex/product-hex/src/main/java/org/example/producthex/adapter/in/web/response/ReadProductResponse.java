package org.example.producthex.adapter.in.web.response;

import lombok.*;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadProductResponse {
    private Long id;
    private String name;
    private String description;

    private List<String> productImages;

}
