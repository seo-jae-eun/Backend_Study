package org.example.common.events.create;

import lombok.Data;
import org.example.common.dto.PaymentDetailDTO;

import java.util.List;

@Data
public class CreatedPaymentEvent {
    private String paymentId;
    private String orderId;
    private int totalPaymentAmt;
    private List<PaymentDetailDTO> paymentDetails;
    private boolean isCompensation;
}
