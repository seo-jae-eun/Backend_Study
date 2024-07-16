package org.example.orderservice.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FailedCompleteUpdateOrderEvent {
    private String orderId;
}
