package org.example.common.command.create;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.example.common.dto.DeliveryStatusEnum;

@Value
@Builder
public class CancelCreateDeliveryCommand {
    @TargetAggregateIdentifier
    String deliveryId;
    String orderId;
    String deliveryStatus = DeliveryStatusEnum.CANCELED.value();
}
