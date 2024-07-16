package org.example.common.events.create;

import lombok.Data;

@Data
public class CreatedProductEvent {
    private String productId;
    private String productName;
    private int unitPrice;
    private int productQty;
}
