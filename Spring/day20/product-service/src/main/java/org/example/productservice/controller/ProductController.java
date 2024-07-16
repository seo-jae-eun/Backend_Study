package org.example.productservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.example.common.command.create.CreateProductCommand;
import org.example.common.vo.ResultVO;
import org.example.productservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Product service API", description = "Product Application")
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private transient final CommandGateway commandGateway;
    @Autowired
    public ProductController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/register")
    @Operation(summary = "신규 제품 등록")
    private ResultVO<CreateProductCommand> register(@RequestBody Product product) {
        log.info("[@PostMapping(\"/register\")] Executing register: {}", product.toString());

        ResultVO<CreateProductCommand> retVo = new ResultVO<>();
        try {
            CreateProductCommand createProductCommand = CreateProductCommand.builder()
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .unitPrice(product.getUnitPrice())
                    .productQty(product.getProductQty())
                    .build();
            commandGateway.sendAndWait(createProductCommand);
            retVo.setReturnCode(true);
            retVo.setReturnMessage("Success to register product");
            retVo.setResult(createProductCommand);
        } catch(Exception e) {
            retVo.setReturnCode(false);
            retVo.setReturnMessage(e.getMessage());
        }
        return retVo;
    }
}
