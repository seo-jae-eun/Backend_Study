package org.example.axonpractice.axon.aggregate;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.example.axonpractice.axon.command.CreateProductCommand;
import org.example.axonpractice.axon.command.PurchaseProductCommand;
import org.example.axonpractice.axon.command.RestoreProductCommand;
import org.example.axonpractice.axon.events.CreatedProductEvent;
import org.example.axonpractice.axon.events.PurchasedProductEvent;
import org.example.axonpractice.axon.events.RestoredProductEvent;

@Slf4j
@Aggregate
@NoArgsConstructor
public class ProductAggregate {
    @AggregateIdentifier
    private String id;

    @AggregateMember
    private String name;
    @AggregateMember
    private Integer price;
    @AggregateMember
    private Integer amount;


    // 커맨드 처리(@CommandHandler) -> 이벤트 발행(AggregateLifecycle.apply(이벤트 객체), 해당 서비스에서만 처리 가능)
    @CommandHandler
    private ProductAggregate(CreateProductCommand command) {
        log.info("[@CommandHandler - ProductAggregate] CreateProductCommand for Id : {}", command.getId());
        CreatedProductEvent event = CreatedProductEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .price(command.getPrice())
                .amount(command.getAmount())
                .build();

        AggregateLifecycle.apply(event);
    }


    // 이벤트 발행할 때 현재 어그리게이트 상태 변경(@EventSourcingHandler)
    // 이벤트 소싱 : Axon의 트래킹 이벤트 프로세서가 실행되면서 이벤트를 다시 Replay
    @EventSourcingHandler
    private void on(CreatedProductEvent event) {
        log.info("[@EventSourcingHandler - on] CreatedProductEvent for Id : {}", event.getId());
        this.id = event.getId();
        this.name = event.getName();
        this.price = event.getPrice();
        this.amount = event.getAmount();
    }

    @CommandHandler
    private void handle(PurchaseProductCommand command) {
        log.info("[@CommandHandler - handle] PurchaseProductCommand for Id : {}", command.getId());
        PurchasedProductEvent event = PurchasedProductEvent.builder()
                .id(command.getId())
                .amount(command.getAmount())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    private void on(PurchasedProductEvent event) {
        log.info("[@EventSourcingHandler - on] PurchasedProductEvent for Id : {}", event.getId());
        this.id = event.getId();
        log.info("[구매] 구매 전 수량 : {}", this.amount);
        this.amount = this.amount - event.getAmount();
        log.info("[구매] 구매 후 수량 : {}", this.amount);

    }



    @CommandHandler
    private void handle(RestoreProductCommand command) {
        log.info("[@CommandHandler - handle] RestoreProductCommand for Id : {}", command.getId());
        RestoredProductEvent event = RestoredProductEvent.builder()
                .id(command.getId())
                .amount(command.getAmount())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    private void on(RestoredProductEvent event) {
        log.info("[@EventSourcingHandler - on] RestoredProductEvent for Id : {}", event.getId());
        this.id = event.getId();
        log.info("[복구] 복구 전 수량 : {}", this.amount);
        this.amount = this.amount + event.getAmount();
        log.info("[복구] 복구 후 수량 : {}", this.amount);

    }

}
