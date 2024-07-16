package org.example.common.command.update;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class UpdateProductQtyCommand {
    @TargetAggregateIdentifier
    String productId;
    String adjustType;
    int adjustQty;
}
