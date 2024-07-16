package org.example.common.command.delete;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class CancelDeleteReportCommand {
    @TargetAggregateIdentifier
    String reportId;
    String orderId;
    boolean isCompensation;
}
