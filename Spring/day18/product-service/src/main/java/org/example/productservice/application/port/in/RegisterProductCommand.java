package org.example.productservice.application.port.in;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterProductCommand {
    private String name;
    private Integer price;
}
