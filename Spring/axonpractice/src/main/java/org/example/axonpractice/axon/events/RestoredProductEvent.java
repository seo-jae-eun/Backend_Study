package org.example.axonpractice.axon.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestoredProductEvent {
    private String id;
    private Integer amount;
}
