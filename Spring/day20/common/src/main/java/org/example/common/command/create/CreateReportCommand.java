package org.example.common.command.create;


import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.example.common.dto.OrderDetailDTO;
import org.example.common.dto.PaymentDetailDTO;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class CreateReportCommand {
    @TargetAggregateIdentifier
    String reportId;

    String orderId;
    String userId;
    LocalDateTime orderDatetime;
    int totalOrderAmt;
    String orderStatus;
    List<OrderDetailDTO> orderDetails;
    String paymentId;
    int totalPaymentAmt;
    String paymentStatus;
    List<PaymentDetailDTO> paymentDetails;
    String deliveryId;
    String deliveryStatus;
    boolean isCompensation;
}
