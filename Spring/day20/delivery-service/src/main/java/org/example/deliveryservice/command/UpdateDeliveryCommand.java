package org.example.deliveryservice.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class UpdateDeliveryCommand {
    @TargetAggregateIdentifier
    String deliveryId;
    String orderId;
    String deliveryStatus;
}
