package org.example.axonpractice.axon.events;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FailedPurchaseProductEvent {
    private String id;
    private Integer amount;
}
