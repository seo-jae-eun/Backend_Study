package org.example.common.events.update;

import lombok.Data;

@Data
public class UpdatedProductQtyEvent {
    private String productId;
    private String adjustType;
    private int adjustQty;
}
