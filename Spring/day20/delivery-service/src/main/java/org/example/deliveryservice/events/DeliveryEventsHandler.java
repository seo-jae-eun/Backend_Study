package org.example.deliveryservice.events;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.example.common.command.update.UpdateReportDeliveryStatusCommand;
import org.example.common.events.create.CancelledCreateDeliveryEvent;
import org.example.common.events.create.CreatedDeliveryEvent;
import org.example.common.events.create.FailedCreateDeliveryEvent;
import org.example.common.events.delete.DeletedDeliveryEvent;
import org.example.common.events.delete.FailedDeleteDeliveryEvent;
import org.example.common.events.update.CancelledUpdatePaymentEvent;
import org.example.common.queries.GetReportId;
import org.example.deliveryservice.entity.Delivery;
import org.example.deliveryservice.repository.DeliveryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@ProcessingGroup("delivery")
@AllowReplay
public class DeliveryEventsHandler {

    private final DeliveryRepository deliveryRepository;
    private transient final CommandGateway commandGateway;
    private transient final EventGateway eventGateway;
    private transient final QueryGateway queryGateway;
    @Autowired
    public DeliveryEventsHandler(DeliveryRepository deliveryRepository, CommandGateway commandGateway,
                                 EventGateway eventGateway, QueryGateway queryGateway) {
        this.deliveryRepository = deliveryRepository;
        this.commandGateway = commandGateway;
        this.eventGateway = eventGateway;
        this.queryGateway = queryGateway;
    }
    @EventHandler
    private void on(CreatedDeliveryEvent event) {
        log.info("[@EventHandler] Handle CreatedDeliveryEvent");
        try {
            Delivery delivery = new Delivery();
            BeanUtils.copyProperties(event, delivery);
            deliveryRepository.save(delivery);
        } catch(Exception e) {
            log.error("Error is occurred during handle CreatedDeliveryEvent: {}", e.getMessage());
            if(!event.isCompensation()) {   //보상처리가 아닌 경우만 수행
                eventGateway.publish(new FailedCreateDeliveryEvent(event.getDeliveryId(), event.getOrderId()));
            }
        }
    }

    @EventHandler
    private void on(CancelledCreateDeliveryEvent event) {
        log.info("[@EventHandler] Handle CancelledCreateDeliveryEvent");

        Delivery delivery = getEntity(event.getDeliveryId());
        if(delivery != null) {
            deliveryRepository.delete(delivery);
        }
    }

    @EventHandler
    private void on(UpdatedDeliveryEvent event) {
        log.info("[DeliveryEventsHandler] Handle <UpdatedDeliveryEvent> for Delivery Id: {}", event.getDeliveryId());

        Delivery delivery = getEntity(event.getDeliveryId());
        if(delivery != null) {
            delivery.setDeliveryStatus(event.getDeliveryStatus());
            deliveryRepository.save(delivery);

            //-- Send UpdateReportDeliveryStatusCommand to Report service
            String reportId = queryGateway.query(new GetReportId(event.getOrderId()),
                    ResponseTypes.instanceOf(String.class)).join();
            if("".equals(reportId)) {
                log.info("Can't get Report Id for Order Id: {}", event.getOrderId());
                return;
            }
            UpdateReportDeliveryStatusCommand cmd = UpdateReportDeliveryStatusCommand.builder()
                    .reportId(reportId)
                    .orderId(event.getOrderId())
                    .deliveryStatus(event.getDeliveryStatus())
                    .build();

            commandGateway.send(cmd);
        }
    }

    @EventHandler
    private void on(CancelledUpdatePaymentEvent event) {
        log.info("[DeliveryEventsHandler] Handle <CancelledUpdatePaymentEvent> for Order Id: {}", event.getOrderId());

    }

    @EventHandler
    private void on(DeletedDeliveryEvent event) {
        log.info("[DeliveryEventsHandler] Handle <DeletedDeliveryEvent> for Delivery Id: {}", event.getDeliveryId());

        Delivery delivery = getEntity(event.getDeliveryId());
        if(delivery == null) {
            eventGateway.publish(new FailedDeleteDeliveryEvent(event.getDeliveryId(), event.getOrderId()));
            return;
        }

        try {
            deliveryRepository.delete(delivery);
        } catch(Exception e) {
            deliveryRepository.delete(delivery);
            eventGateway.publish(new FailedDeleteDeliveryEvent(event.getDeliveryId(), event.getOrderId()));
        }
    }

    private Delivery getEntity(String deliveryId) {
        Delivery delivery = null;
        Optional<Delivery> optDelivery = deliveryRepository.findById(deliveryId);
        if(optDelivery.isPresent()) {
            delivery = optDelivery.get();
        } else {
            log.info("Can't get entry for Delivery Id: {}", deliveryId);
        }
        return delivery;
    }

    @ResetHandler
    private void replayAll() {
        log.info("[DeliveryEventHandler] Executing replayAll");
        deliveryRepository.deleteAll();
    }
}

