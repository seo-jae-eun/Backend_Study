package org.example.common.command.update;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.example.common.dto.PaymentDetailDTO;

import java.util.List;

@Value
@Builder
public class UpdatePaymentCommand {
    @TargetAggregateIdentifier
    String paymentId;

    String orderId;
    int totalPaymentAmt;
    String paymentStatus;
    List<PaymentDetailDTO> paymentDetails;
    boolean isCompensation;
}
