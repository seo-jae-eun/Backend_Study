package org.example.axonpractice.product;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.example.axonpractice.axon.command.CreateProductCommand;
import org.example.axonpractice.axon.command.PurchaseProductCommand;
import org.example.axonpractice.axon.query.GetProductQuery;
import org.example.axonpractice.product.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    private ResponseEntity<CreateProductCommand> create(@RequestBody ProductDto dto) {
        String uuid = UUID.randomUUID().toString();

        CreateProductCommand command = CreateProductCommand.builder()
                .id(uuid)
                .name(dto.getName())
                .price(dto.getPrice())
                .amount(dto.getAmount())
                .build();

        commandGateway.sendAndWait(command, 300, TimeUnit.SECONDS);
        return ResponseEntity.ok(command);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/purchase")
    private ResponseEntity<PurchaseProductCommand> purchase(String id, Integer amount) {

        PurchaseProductCommand command = PurchaseProductCommand.builder()
                .id(id)
                .amount(amount)
                .build();

        commandGateway.sendAndWait(command, 300, TimeUnit.SECONDS);
        return ResponseEntity.ok(command);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    private ResponseEntity<List<ProductDto>> list() {
        List<ProductDto> productDtos = queryGateway.query("list", "", ResponseTypes.multipleInstancesOf(ProductDto.class)).join();

        return ResponseEntity.ok(productDtos);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    private ResponseEntity<ProductDto> get(String id) {
        GetProductQuery query = GetProductQuery.builder()
                .id(id)
                .build();
        ProductDto productDto = queryGateway.query(query, ResponseTypes.instanceOf(ProductDto.class)).join();

        return ResponseEntity.ok(productDto);
    }
}