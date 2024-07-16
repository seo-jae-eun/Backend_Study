package org.example.orderservice.events;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.dto.OrderDetailDTO;
import org.example.common.dto.PaymentDetailDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class UpdatedOrderEvent {
    private String orderId;
    private String paymentId;
    private LocalDateTime orderDatetime;
    private int totalOrderAmt;
    private List<OrderDetailDTO> orderDetails;
    private List<PaymentDetailDTO> paymentDetails;
    private int totalPaymentAmt;
    private String orderStatus;
    private boolean isCompensation;
}
