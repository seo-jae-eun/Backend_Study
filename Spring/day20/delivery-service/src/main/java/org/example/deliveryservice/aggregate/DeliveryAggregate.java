package org.example.deliveryservice.aggregate;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.example.common.command.create.CancelCreateDeliveryCommand;
import org.example.common.command.create.CreateDeliveryCommand;
import org.example.common.command.delete.CancelDeleteDeliveryCommand;
import org.example.common.command.delete.DeleteDeliveryCommand;
import org.example.common.dto.DeliveryDTO;
import org.example.common.dto.DeliveryStatusEnum;
import org.example.common.events.create.CancelledCreateDeliveryEvent;
import org.example.common.events.create.CreatedDeliveryEvent;
import org.example.common.events.delete.DeletedDeliveryEvent;
import org.example.deliveryservice.command.UpdateDeliveryCommand;
import org.example.deliveryservice.events.UpdatedDeliveryEvent;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Aggregate(snapshotTriggerDefinition = "snapshotTrigger", cache = "snapshotCache")
//@Aggregate
public class DeliveryAggregate {
    @AggregateIdentifier
    private String deliveryId;
    @AggregateMember
    private String orderId;
    @AggregateMember
    private String deliveryStatus;

    private final List<DeliveryDTO> aggregateHistory = new ArrayList<>();

    public DeliveryAggregate() {

    }

    //================= 배송 정보 생성 요청 Command 처리 ==================

    @CommandHandler
    private DeliveryAggregate(CreateDeliveryCommand createDeliveryCommand) {
        log.info("[@CommandHandler] Executing <CreateDeliveryCommand> for Order Id: {} and Delivery ID: {}",
                createDeliveryCommand.getOrderId(), createDeliveryCommand.getDeliveryId());

        CreatedDeliveryEvent createdDeliveryEvent = new CreatedDeliveryEvent();
        BeanUtils.copyProperties(createDeliveryCommand, createdDeliveryEvent);

        AggregateLifecycle.apply(createdDeliveryEvent);
    }

    @EventSourcingHandler
    private void on(CreatedDeliveryEvent event) {
        log.info("[@EventSourcingHandler] Executing <CreatedDeliveryEvent> for Order Id: {} and Delivery Id: {}",
                event.getOrderId(), event.getDeliveryId());

        this.orderId = event.getOrderId();
        this.deliveryId = event.getDeliveryId();
        this.deliveryStatus = event.getDeliveryStatus();

        this.aggregateHistory.add(cloneAggregate(this));
    }

    @CommandHandler
    private void handle(CancelCreateDeliveryCommand cancelCreateDeliveryCommand) {
        log.info("[@CommandHandler] Executing <CancelCreateDeliveryCommand> for Order Id : {} and Delivery Id: {}",
                cancelCreateDeliveryCommand.getOrderId(), cancelCreateDeliveryCommand.getDeliveryId());

        CancelledCreateDeliveryEvent cancelledCreateDeliveryEvent = new CancelledCreateDeliveryEvent();
        BeanUtils.copyProperties(cancelCreateDeliveryCommand, cancelledCreateDeliveryEvent);

        AggregateLifecycle.apply(cancelledCreateDeliveryEvent);
    }

    @EventSourcingHandler
    private void on(CancelledCreateDeliveryEvent event) {
        log.info("[@EventSourcingHandler] Executing <CancelledCreateDeliveryEvent> for Order Id : {} and Delivery Id: {}",
                event.getOrderId(), event.getDeliveryId());
        this.deliveryStatus = event.getDeliveryStatus();
    }

    //================= 배송 정보 수정 요청 Command 처리 ==========================

    @CommandHandler
    private void handle(UpdateDeliveryCommand updateDeliveryCommand) {
        log.info("[@CommandHandler] Executing <DeliveryUpdateCommand> for Delivery Id : {}", updateDeliveryCommand.getDeliveryId());

        if (updateDeliveryCommand.getDeliveryStatus().equals(this.deliveryStatus)) {
            log.info("Delivery Status is already same value <{}>. So, This command is ignored", this.deliveryStatus);
            return;
        }
        UpdatedDeliveryEvent updatedDeliveryEvent = new UpdatedDeliveryEvent();
        BeanUtils.copyProperties(updateDeliveryCommand, updatedDeliveryEvent);

        AggregateLifecycle.apply(updatedDeliveryEvent);
    }

    @EventSourcingHandler
    private void on(UpdatedDeliveryEvent event) {
        log.info("[@EventSourcingHandler] Executing DeliveryUpdateEvent for Delivery Id : {}", event.getDeliveryId());
        this.deliveryStatus = event.getDeliveryStatus();

        //-- 수정 또는 삭제 실패 시 이전 정보로 rollback시에만 사용되므로 바로 이전 정보만 담고 있으면 됨
        this.aggregateHistory.clear();
        this.aggregateHistory.add(cloneAggregate(this));
    }

    //================== 배송 정보 삭제 요청 Command 처리 =====================
    @CommandHandler
    private void handle(DeleteDeliveryCommand deleteDeliveryCommand) {
        log.info("[@EventSourcingHandler] Executing DeleteDeliveryCommand for Delivery Id : {}", deleteDeliveryCommand.getDeliveryId());

        AggregateLifecycle.apply(new DeletedDeliveryEvent(deleteDeliveryCommand.getDeliveryId(), deleteDeliveryCommand.getOrderId()));
    }

    @EventSourcingHandler
    private void on(DeletedDeliveryEvent event) {
        log.info("[@EventSourcingHandler] Executing DeletedPaymentEvent for Delivery Id : {}", event.getDeliveryId());
        this.deliveryStatus = DeliveryStatusEnum.ORDER_CANCLLED.value();
        //AggregateLifecycle.markDeleted();
    }

    @CommandHandler
    private void handle(CancelDeleteDeliveryCommand cancelDeleteDeliveryCommand) {
        log.info("[@EventSourcingHandler] Executing CancelDeleteDeliveryCommand for Delivery Id : {}", cancelDeleteDeliveryCommand.getOrderId());

        //-- 삭제 취소시 rollback은 이전 배송정보를 생성하는 것이므로 이전 배송정보를 읽어 생성 요청 Event를 발행
        if (this.aggregateHistory.isEmpty()) return;
        DeliveryDTO delivery = this.aggregateHistory.get(this.aggregateHistory.size() - 1);

        CreatedDeliveryEvent event = new CreatedDeliveryEvent();
        event.setDeliveryId(delivery.getDeliveryId());
        event.setOrderId(delivery.getOrderId());
        event.setDeliveryStatus(delivery.getDeliveryStatus());
        event.setCompensation(true);
        AggregateLifecycle.apply(event);
    }

    //========= Aggregate 객체 복사
    private DeliveryDTO cloneAggregate(DeliveryAggregate deliveryAggregate) {
        DeliveryDTO delivery = new DeliveryDTO();
        delivery.setDeliveryId(deliveryAggregate.deliveryId);
        delivery.setOrderId(deliveryAggregate.orderId);
        delivery.setDeliveryStatus(deliveryAggregate.deliveryStatus);
        return delivery;
    }
}