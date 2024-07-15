package org.example.axonpractice.axon.events;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatedProductEvent {
    private String id;
    private String name;
    private Integer price;
    private Integer amount;
}
