package org.example.deliveryservice.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.example.common.command.update.UpdateProductQtyCommand;
import org.example.common.config.Constants;
import org.example.common.dto.DeliveryDTO;
import org.example.common.dto.DeliveryStatusEnum;
import org.example.common.dto.OrderDetailDTO;
import org.example.common.dto.ProductQtyAdjustTypeEnum;
import org.example.common.vo.ResultVO;
import org.example.deliveryservice.command.UpdateDeliveryCommand;
import org.example.deliveryservice.entity.Delivery;
import org.example.deliveryservice.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class DeliveryService {

    private transient final CommandGateway commandGateway;
    private transient final QueryGateway queryGateway;
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(CommandGateway commandGateway, QueryGateway queryGateway, DeliveryRepository deliveryRepository) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.deliveryRepository = deliveryRepository;
    }

    public ResultVO<Delivery> getDelivery(String orderId) {
        log.info("[DeliveryService] Executing <getDelivery> for Order Id: {}", orderId);
        ResultVO<Delivery> retVo = new ResultVO<>();
        Optional<Delivery> optDelivery = deliveryRepository.findByOrderId(orderId);
        if(optDelivery.isPresent()) {
            Delivery delivery = optDelivery.get();
            retVo.setReturnCode(true);
            retVo.setReturnMessage("Find");
            retVo.setResult(delivery);
        } else {
            retVo.setReturnCode(false);
            retVo.setReturnMessage("Can't find delivery info for Order Id <"+orderId+">");
        }
        return retVo;
    }

    public ResultVO<DeliveryDTO> updateDeliveryStatus(DeliveryDTO deliveryDTO) {
        log.info("[DeliveryService] Executing updateDeliveryStatus for Delivery Id: {}", deliveryDTO.getDeliveryId());

        ResultVO<DeliveryDTO> retVo;

        //-- Devering 상태로 변경 시 Product의 재고량을 줄이는 요청을 먼저 수행
        if(DeliveryStatusEnum.DELIVERING.value().equals(deliveryDTO.getDeliveryStatus())) {
            retVo = updateProductQty(deliveryDTO);
            if(retVo.isReturnCode()) {
                retVo = update(deliveryDTO);
            }
        } else {
            retVo = update(deliveryDTO);
        }
        return retVo;
    }

    private ResultVO<DeliveryDTO> update(DeliveryDTO deliveryDTO) {
        log.info("[DeliveryService] Executing <update> for Delivery Id: {}", deliveryDTO.getDeliveryId());

        ResultVO<DeliveryDTO> retVo = new ResultVO<>();
        try {
            UpdateDeliveryCommand updateDeliveryCommand = UpdateDeliveryCommand.builder()
                    .deliveryId(deliveryDTO.getDeliveryId())
                    .orderId(deliveryDTO.getOrderId())
                    .deliveryStatus(deliveryDTO.getDeliveryStatus())
                    .build();
            commandGateway.sendAndWait(updateDeliveryCommand, Constants.GATEWAY_TIMEOUT, TimeUnit.SECONDS);
            retVo.setReturnCode(true);
            retVo.setReturnMessage("Update delivery status to <"+deliveryDTO.getDeliveryStatus()+"> is success!");
            retVo.setResult(deliveryDTO);
        } catch(Exception e) {
            retVo.setReturnCode(false);
            retVo.setReturnMessage(e.getMessage());
        }
        return retVo;
    }

    private ResultVO<DeliveryDTO> updateProductQty(DeliveryDTO deliveryDTO) {
        log.info("[DeliveryService] Executing <updateProductQty> for Delivery Id: {}", deliveryDTO.getDeliveryId());

        ResultVO<DeliveryDTO> retVo = new ResultVO<>();

        try {
            List<OrderDetailDTO> orderDetails = queryGateway.query(
                    Constants.QUERY_ORDER_DETAIL,
                    deliveryDTO.getOrderId(),
                    ResponseTypes.multipleInstancesOf(OrderDetailDTO.class)).join();
            if(orderDetails == null) {
                retVo.setReturnCode(false);
                retVo.setReturnMessage("Can't find Order detail info for Order Id <"+deliveryDTO.getDeliveryId()+">");
                return retVo;
            }

            log.info("Get Order details: {}", orderDetails);
            UpdateProductQtyCommand cmd;
            List<OrderDetailDTO> successList = new ArrayList<>();
            for(OrderDetailDTO item:orderDetails) {
                cmd = UpdateProductQtyCommand.builder()
                        .productId(item.getProductId())
                        .adjustType(ProductQtyAdjustTypeEnum.DECREASE.value())
                        .adjustQty(item.getQty())
                        .build();
                try {
                    commandGateway.sendAndWait(cmd, Constants.GATEWAY_TIMEOUT, TimeUnit.SECONDS);
                    successList.add(item);
                } catch(Exception e) {
                    log.error("Fail to send <ProductQtyUpdateCommand>: {}", e.getMessage());
                }
            }

            //실패한 처리가 있을때는 rollback 처리함
            if(successList.size() < orderDetails.size()) {
                for(OrderDetailDTO item:successList) {
                    cmd = UpdateProductQtyCommand.builder()
                            .productId(item.getProductId())
                            .adjustType(ProductQtyAdjustTypeEnum.INCREASE.value())
                            .adjustQty(item.getQty())
                            .build();
                    commandGateway.sendAndWait(cmd, Constants.GATEWAY_TIMEOUT, TimeUnit.SECONDS);
                }
                retVo.setReturnCode(false);
                retVo.setReturnMessage("Fail to update product quantity");
            } else {
                retVo.setReturnCode(true);
                retVo.setReturnMessage("Success to update product quantity");
            }
        } catch(Exception e) {
            retVo.setReturnCode(false);
            retVo.setReturnMessage(e.getMessage());
        }
        return retVo;
    }
}

