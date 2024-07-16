package org.example.common.command.create;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.example.common.dto.OrderStatusEnum;

@Value
@Builder
public class CancelCreateOrderCommand {
    @TargetAggregateIdentifier
    String orderId;
    String orderStatus = OrderStatusEnum.FAILED.value();
}
