package org.example.orderservice.events;

import lombok.Data;

@Data
public class CompletedUpdateOrderEvent {
    private String orderId;
    private String orderStatus;
}
