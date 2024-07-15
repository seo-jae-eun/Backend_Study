package org.example.axonpractice.axon.events;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.spring.stereotype.Saga;
import org.example.axonpractice.axon.command.RestoreProductCommand;
import org.example.axonpractice.product.Product;
import org.example.axonpractice.product.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEventHandler {
    private final ProductRepository productRepository;

    private final CommandGateway commandGateway;

    // SAGA 패턴을 쓸 때(참여자), CQRS를 구현할 때
    private final EventGateway eventGateway;



    @EventHandler
    private void on(CreatedProductEvent event) {
        log.info("[@EventHandler - on] CreatedProductEvent for Id : {}", event.getId());
        // DB에 저장
        Product product = Product.builder()
                .id(event.getId())
                .name(event.getName())
                .price(event.getPrice())
                .amount(event.getAmount())
                .build();

        productRepository.save(product);
    }

    @EventHandler
    private void on(PurchasedProductEvent event) {
        log.info("[@EventHandler - on] PurchasedProductEvent for Id : {}", event.getId());

        Optional<Product> result = productRepository.findById(event.getId());
        if(result.isPresent()) {
            Product origin = result.get();
            Product product = Product.builder()
                    .id(event.getId())
                    .name(origin.getName())
                    .price(origin.getPrice())
                    .amount(origin.getAmount() - event.getAmount())
                    .build();
            productRepository.save(product);

            // 트랜잭션 처리 - 저장한 데이터가 잘못되면 저장한 걸 취소할 수 있도록 보상 트랜잭션을 실행
            if(product.getAmount() < 0) {
                log.info("[보상 트랜잭션] PurchasedProductEvent for Id : {}", event.getId());
                FailedPurchaseProductEvent failedEvent = FailedPurchaseProductEvent.builder()
                        .id(event.getId()).amount(event.getAmount()).build();
                eventGateway.publish(failedEvent);
                return;
            }

        }
    }

    @EventHandler
    private void on(FailedPurchaseProductEvent event) {
        log.info("[@EventHandler - on] FailedPurchaseProductEvent for Id : {}", event.getId());
        RestoreProductCommand command = RestoreProductCommand.builder().id(event.getId()).amount(event.getAmount()).build();
        commandGateway.send(command);
    }

    @EventHandler
    private void on(RestoredProductEvent event) {
        log.info("[@EventHandler - on] RestoredProductEvent for Id : {}", event.getId());
        Optional<Product> result = productRepository.findById(event.getId());
        if(result.isPresent()) {
            Product origin = result.get();
            Product product = Product.builder()
                    .id(event.getId())
                    .name(origin.getName())
                    .price(origin.getPrice())
                    .amount(origin.getAmount() + event.getAmount())
                    .build();
            productRepository.save(product);
        }
    }

}
