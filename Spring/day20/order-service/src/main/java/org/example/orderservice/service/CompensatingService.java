package org.example.orderservice.service;
/*
- 목적: 주문 신규/수정/삭제 처리 실패 시 보상 처리
- 설명:
    - 보상 처리를 요청하는 Command객체를 생성하여 발송함
    - Command Handler가 있는 서비스의 Aggregate에서 요청을 처리함
*/

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.example.common.command.create.CancelCreateDeliveryCommand;
import org.example.common.command.create.CancelCreateOrderCommand;
import org.example.common.command.create.CancelCreatePaymentCommand;
import org.example.common.command.create.CreateReportCommand;
import org.example.common.command.delete.CancelDeleteDeliveryCommand;
import org.example.common.command.delete.CancelDeletePaymentCommand;
import org.example.common.command.update.CancelUpdatePaymentCommand;
import org.example.common.command.update.UpdateReportCommand;
import org.example.common.config.Constants;
import org.example.common.dto.DeliveryDTO;
import org.example.common.dto.OrderDTO;
import org.example.common.dto.PaymentDTO;
import org.example.common.dto.ServiceNameEnum;
import org.example.common.queries.GetReportId;
import org.example.orderservice.command.CancelDeleteOrderCommand;
import org.example.orderservice.command.CancelUpdateOrderCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CompensatingService {
    private transient final CommandGateway commandGateway;
    private transient final QueryGateway queryGateway;
    @Autowired
    public CompensatingService(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    //==================== 주문 생성 보상 처리 ====================
    public void cancelCreateOrder(HashMap<String, String> aggregateIdMap) {
        log.info("[CompensatingService] Executing <cancelCreateOrder> for Order Id: {}", aggregateIdMap.get(ServiceNameEnum.ORDER.value()));

        try {
            CancelCreateOrderCommand cancelCreateOrderCommand = CancelCreateOrderCommand.builder()
                    .orderId(aggregateIdMap.get(ServiceNameEnum.ORDER.value())).build();
            commandGateway.sendAndWait(cancelCreateOrderCommand, Constants.GATEWAY_TIMEOUT, TimeUnit.SECONDS);
        } catch(Exception e) {
            log.error("Error is occurred during <cancelCreateOrder>: {}", e.getMessage());
        }
    }

    public void cancelCreatePayment(HashMap<String, String> aggregateIdMap) {
        log.info("[CompensatingService] Executing <cancelCreatePayment> for Order Id: {}", aggregateIdMap.get(ServiceNameEnum.ORDER.value()));

        try {
            //do compensating transaction: Payment
            CancelCreatePaymentCommand cancelCreatePaymentCommand = CancelCreatePaymentCommand.builder()
                    .paymentId(aggregateIdMap.get(ServiceNameEnum.PAYMENT.value()))
                    .orderId(aggregateIdMap.get(ServiceNameEnum.ORDER.value()))
                    .build();
            commandGateway.sendAndWait(cancelCreatePaymentCommand, Constants.GATEWAY_TIMEOUT, TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error("Error is occurred during <cancelCreatePayment>: {}", e.getMessage());
        }
    }

    public void cancelCreateDelivery(HashMap<String, String> aggregateIdMap) {
        log.info("[CompensatingService] Executing <cancelCreateDelivery> for Order Id: {}", aggregateIdMap.get(ServiceNameEnum.ORDER.value()));
        try {
            //compensating transaction: Delivery
            CancelCreateDeliveryCommand cancelCreateDeliveryCommand = CancelCreateDeliveryCommand.builder()
                    .deliveryId(aggregateIdMap.get(ServiceNameEnum.DELIVERY.value()))
                    .orderId(aggregateIdMap.get(ServiceNameEnum.ORDER.value()))
                    .build();
            commandGateway.sendAndWait(cancelCreateDeliveryCommand, Constants.GATEWAY_TIMEOUT, TimeUnit.SECONDS);
        } catch(Exception e) {
            log.error("Error is occurred during <cancelCreateDelivery>: {}", e.getMessage());
        }
    }

    //==================== 주문 수정 보상 처리 ====================
    public void cancelUpdateOrder(HashMap<String, String> aggregateIdMap) {
        log.info("[CompensatingService] Executing <cancelUpdateOrder> for Order Id: {}", aggregateIdMap.get(ServiceNameEnum.ORDER.value()));
        try {
            commandGateway.sendAndWait(CancelUpdateOrderCommand.builder()
                    .orderId(aggregateIdMap.get(ServiceNameEnum.ORDER.value())).isCompensation(true)
                    .build(), Constants.GATEWAY_TIMEOUT, TimeUnit.SECONDS);

        } catch(Exception e) {
            log.error("Error is occurred during <cancelUpdateOrder>: {}", e.getMessage());
        }
    }

    public void cancelUpdatePayment(HashMap<String, String> aggregateIdMap) {
        log.info("[CompensatingService] Executing <cancelUpdatePayment> for Order Id: {}", aggregateIdMap.get(ServiceNameEnum.ORDER.value()));
        try {
            commandGateway.sendAndWait(CancelUpdatePaymentCommand.builder()
                    .paymentId(aggregateIdMap.get(ServiceNameEnum.PAYMENT.value()))
                    .orderId(aggregateIdMap.get(ServiceNameEnum.ORDER.value()))
                    .isCompensation(true)
                    .build(), Constants.GATEWAY_TIMEOUT, TimeUnit.SECONDS);

        } catch(Exception e) {
            log.error("Error is occurred during <cancelUpdatePayment>: {}", e.getMessage());
        }
    }

    //==================== 주문 삭제 보상 처리 ====================
    public void cancelDeleteOrder(HashMap<String, String> aggregateIdMap) {
        log.info("[CompensatingService] Executing <cancelDeleteOrder> for Order Id: {}", aggregateIdMap.get(ServiceNameEnum.ORDER.value()));

        try {
            commandGateway.sendAndWait(CancelDeleteOrderCommand.builder()
                    .orderId(aggregateIdMap.get(ServiceNameEnum.ORDER.value()))
                    .isCompensation(true).build(),
                    Constants.GATEWAY_TIMEOUT, TimeUnit.SECONDS);

        } catch(Exception e) {
            log.error(e.getMessage());
        }
    }

    public void cancelDeletePayment(HashMap<String, String> aggregateIdMap) {
        log.info("[CompensatingService] Executing <cancelDeletePayment> for Order Id: {}", aggregateIdMap.get(ServiceNameEnum.ORDER.value()));

        try {
            commandGateway.sendAndWait(CancelDeletePaymentCommand.builder()
                    .paymentId(aggregateIdMap.get(ServiceNameEnum.PAYMENT.value()))
                    .isCompensation(true)
                    .build(), Constants.GATEWAY_TIMEOUT, TimeUnit.SECONDS);
        } catch(Exception e) {
            log.error(e.getMessage());
        }
    }

    public void cancelDeleteDelivery(HashMap<String, String> aggregateIdMap) {
        log.info("[CompensatingService] Executing <cancelDeleteDelivery> for Order Id: {}", aggregateIdMap.get(ServiceNameEnum.ORDER.value()));

        try {
            commandGateway.sendAndWait(CancelDeleteDeliveryCommand.builder()
                    .deleveryId(aggregateIdMap.get(ServiceNameEnum.DELIVERY.value()))
                    .isCompensation(true)
                    .build(), Constants.GATEWAY_TIMEOUT, TimeUnit.SECONDS);

        } catch(Exception e) {
            log.error(e.getMessage());
        }
    }

    public void cancelDeleteReport(HashMap<String, String> aggregateIdMap) {
        log.info("[CompensatingService] Executing <cancelDeleteReport> for Order Id: {}", aggregateIdMap.get(ServiceNameEnum.ORDER.value()));

        updateReport(aggregateIdMap.get(ServiceNameEnum.ORDER.value()), true);
    }

    //==================== 주문, 결제, 배송 레포트 생성/수정 ===========
    public void updateReport(String orderId, boolean isCreate) {
        log.info("===== START {} Report =====", isCreate?"Create":"Update");

        try {
            OrderDTO order = queryGateway.query(Constants.QUERY_REPORT, orderId,
                    ResponseTypes.instanceOf(OrderDTO.class)).join();
            PaymentDTO payment = queryGateway.query(Constants.QUERY_REPORT, orderId,
                    ResponseTypes.instanceOf(PaymentDTO.class)).join();
            DeliveryDTO delivery = queryGateway.query(Constants.QUERY_REPORT, orderId,
                    ResponseTypes.instanceOf(DeliveryDTO.class)).join();

            if(isCreate) {
                CreateReportCommand cmd = CreateReportCommand.builder()
                        .reportId(RandomStringUtils.random(15, false, true))
                        .orderId(order.getOrderId())
                        .userId(order.getUserId())
                        .orderDatetime(order.getOrderDatetime())
                        .totalOrderAmt(order.getTotalOrderAmt())
                        .orderStatus(order.getOrderStatus())
                        .orderDetails(order.getOrderDetails())
                        .paymentId(payment.getPaymentId())
                        .totalPaymentAmt(payment.getTotalPaymentAmt())
                        .paymentStatus(payment.getPaymentStatus())
                        .paymentDetails(payment.getPaymentDetails())
                        .deliveryId(delivery.getDeliveryId())
                        .deliveryStatus(delivery.getDeliveryStatus())
                        .build();
                //commandGateway.sendAndWait(cmd, Constants.GATEWAY_TIMEOUT, TimeUnit.SECONDS);
                commandGateway.send(cmd);
            } else {
                String reportId = queryGateway.query(new GetReportId(orderId),
                        ResponseTypes.instanceOf(String.class)).join();
                if("".equals(reportId)) {
                    log.info("Can't get Report Id for Order Id: {}", orderId);
                    return;
                }
                UpdateReportCommand cmd = UpdateReportCommand.builder()
                        .reportId(reportId)
                        .orderId(order.getOrderId())
                        .userId(order.getUserId())
                        .orderDatetime(order.getOrderDatetime())
                        .totalOrderAmt(order.getTotalOrderAmt())
                        .orderStatus(order.getOrderStatus())
                        .orderDetails(order.getOrderDetails())
                        .paymentId(payment.getPaymentId())
                        .totalPaymentAmt(payment.getTotalPaymentAmt())
                        .paymentStatus(payment.getPaymentStatus())
                        .paymentDetails(payment.getPaymentDetails())
                        .deliveryId(delivery.getDeliveryId())
                        .deliveryStatus(delivery.getDeliveryStatus())
                        .build();
                commandGateway.sendAndWait(cmd, Constants.GATEWAY_TIMEOUT, TimeUnit.SECONDS);
            }

        }  catch(Exception e) {
            log.info(e.getMessage());
        }

        log.info("===== END {} Report =====", isCreate?"Create":"Update");
    }

}
