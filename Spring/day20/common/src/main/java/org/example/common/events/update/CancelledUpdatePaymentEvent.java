package org.example.common.events.update;

import lombok.Data;

@Data
public class CancelledUpdatePaymentEvent {
    private String paymentId;
    private String orderId;
    private boolean isCompensation;
}
