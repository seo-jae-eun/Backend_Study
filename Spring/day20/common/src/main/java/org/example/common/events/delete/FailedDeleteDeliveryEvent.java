package org.example.common.events.delete;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FailedDeleteDeliveryEvent {
    private String deliveryId;
    private String orderId;
}
