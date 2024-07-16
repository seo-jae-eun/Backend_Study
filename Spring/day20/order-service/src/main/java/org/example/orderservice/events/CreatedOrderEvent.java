package org.example.orderservice.events;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.dto.OrderDetailDTO;
import org.example.common.dto.PaymentDetailDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CreatedOrderEvent {
    private String orderId;
    private String userId;
    private LocalDateTime orderDatetime;
    private String orderStatus;
    private int totalOrderAmt;
    private List<OrderDetailDTO> orderDetails;
    private String paymentId;
    private List<PaymentDetailDTO> paymentDetails;
    private int totalPaymentAmt;

    private boolean isCompensation;
}
