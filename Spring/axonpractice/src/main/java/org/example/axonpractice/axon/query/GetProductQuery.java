package org.example.axonpractice.axon.query;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProductQuery {
    private String id;
}
