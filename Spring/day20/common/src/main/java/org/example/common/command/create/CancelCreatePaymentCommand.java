package org.example.common.command.create;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.example.common.dto.PaymentStatusEnum;

@Value
@Builder
public class CancelCreatePaymentCommand {
    @TargetAggregateIdentifier
    String paymentId;
    String orderId;
    String paymentStatus = PaymentStatusEnum.CANCELED.value();
}
