package org.example.axonpractice.axon.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;



@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestoreProductCommand {
    @TargetAggregateIdentifier
    private String id;
    private Integer amount;
}