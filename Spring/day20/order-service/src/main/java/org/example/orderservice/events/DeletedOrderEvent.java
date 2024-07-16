package org.example.orderservice.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletedOrderEvent {
    private String orderId;
}
