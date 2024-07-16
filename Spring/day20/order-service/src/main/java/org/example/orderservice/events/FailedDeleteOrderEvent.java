package org.example.orderservice.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FailedDeleteOrderEvent {
    private String orderId;
}
