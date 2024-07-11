package org.example.producthex.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadProductCommand {
    private Long id;
}
