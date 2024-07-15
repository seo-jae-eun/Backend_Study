package org.example.productreviewservice.application.port.in;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterProductReviewCommand {
    private String name;
    private Integer price;
}
