package org.example.deliveryservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.DeliveryDTO;
import org.example.common.vo.ResultVO;
import org.example.deliveryservice.entity.Delivery;
import org.example.deliveryservice.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Delivery service API", description = "Delivery Application")
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class DeliveryController {

    private final DeliveryService deliveryService;
    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/delivery/{orderId}")
    @Operation(summary = "주문ID에 해당하는 배송정보 읽기")
    @Parameters({
        @Parameter(name = "orderId", in= ParameterIn.PATH, description = "주문ID", required = true)
    })
    private ResponseEntity<ResultVO<Delivery>> getDelivery(
            @PathVariable(name = "orderId") String orderId) {
        log.info("[@GetMapping(\"/delivery/{orderId}\")] Executing <getDelivery>: {}", orderId);
        return new ResponseEntity<>(deliveryService.getDelivery(orderId), HttpStatus.OK);
    }

    @PutMapping("/delivery")
    @Operation(summary = "배송정보 업데이트", description = "deliveryStatus: 10(Created), 20(Cancelled), 30(Delivering), 40(Completed)")
    private ResponseEntity<ResultVO<DeliveryDTO>> updateDeliveryStatus(@RequestBody DeliveryDTO deliveryDTO) {
        log.info("[@PutMapping '/delivery'] Executing updateDelivery: {}", deliveryDTO.toString());

        ResultVO<DeliveryDTO> retVo = deliveryService.updateDeliveryStatus(deliveryDTO);
        return new ResponseEntity<>(retVo, HttpStatus.OK);
    }
}
