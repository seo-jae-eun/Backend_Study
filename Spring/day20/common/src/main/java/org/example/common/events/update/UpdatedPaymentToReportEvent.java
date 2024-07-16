package org.example.common.events.update;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.dto.PaymentDetailDTO;

import java.util.List;

@Data
@NoArgsConstructor
public class UpdatedPaymentToReportEvent {
    private String paymentId;
    private String orderId;
    private int totalPaymentAmt;
    private String paymentStatus;
    private List<PaymentDetailDTO> paymentDetails;
}
