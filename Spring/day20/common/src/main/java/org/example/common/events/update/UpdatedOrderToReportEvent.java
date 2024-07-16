package org.example.common.events.update;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.dto.OrderDetailDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class UpdatedOrderToReportEvent {
    private String orderId;
    private LocalDateTime orderDatetime;
    private int totalOrderAmt;
    private List<OrderDetailDTO> orderDetails;
    private String orderStatus;
}
