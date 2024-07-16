package org.example.productservice.entity;
/*
- 목적: Table과 매핑되는 Entity와 Command Handler인 Aggregate 정의
- 설명
    - Event replay로 최종 상태를 계산하는 일반 Aggregate가 아닌 DB에 최종 상태를 저장하는 State stored Aggregate를 정의
    - Product는 제품 원장 데이터이므로 최종상태만 관리하면 되고 DB 손상시엔 백업 복구등으로 복원할 수 있으므로 Event sourcing 패턴 미적용
*/

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.example.common.command.create.CreateProductCommand;
import org.example.common.command.update.UpdateProductQtyCommand;
import org.example.common.dto.ProductQtyAdjustTypeEnum;

import java.io.Serial;
import java.io.Serializable;

@Slf4j
@Aggregate
@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 2169444340219001818L;

    @Id
    @AggregateIdentifier
    @Column(name = "product_id", nullable = false, length = 10)
    private String productId;

    @AggregateMember
    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @AggregateMember
    @Column(name = "unit_price", nullable = false)
    private int unitPrice;

    @AggregateMember
    @Column(name = "product_qty", nullable = false)
    private int productQty;

    public Product() { }

    @CommandHandler
    private Product(CreateProductCommand createProductCommand) {
        log.info("[@CommandHandler] Executing <CreateProductCommand> for Product Id:{}", createProductCommand.getProductId());

        //--State Stored Aggregator 는 자신의 상태 업데이트를 CommandHandler 에서 수행
        this.productId = createProductCommand.getProductId();
        this.productName = createProductCommand.getProductName();
        this.unitPrice = createProductCommand.getUnitPrice();
        this.productQty = createProductCommand.getProductQty();

    }


    //보상 트랜잭션
    @CommandHandler
    private void handle(UpdateProductQtyCommand updateProductQtyCommand) {
        log.info("[@CommandHandler] Executing <updateProductQtyCommand> for productId:{}", updateProductQtyCommand.getProductId());

        //--State Stored Aggregator 는 자신의 상태 업데이트를 CommandHandler 에서 수행
        if(ProductQtyAdjustTypeEnum.INCREASE.value().equals(updateProductQtyCommand.getAdjustType())) {
            this.productQty += updateProductQtyCommand.getAdjustQty();
        } else if(ProductQtyAdjustTypeEnum.DECREASE.value().equals(updateProductQtyCommand.getAdjustType())) {
            this.productQty -= updateProductQtyCommand.getAdjustQty();
            if(this.productQty < 0) this.productQty = 0;
        }

    }
}
