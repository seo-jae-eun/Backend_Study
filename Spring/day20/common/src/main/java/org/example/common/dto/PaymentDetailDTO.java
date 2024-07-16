package org.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailDTO {
    private String orderId;
    private String paymentId;
    private String paymentKind;
    private int paymentAmt;
}
