package org.example.productservice.queries;
/*
- 목적: Query에 대한 요청을 처리하여 응답
- 설명:
    - Query를 요청할 때 Query명으로 하거나 Query객체로 할 수 있음
    - Query명으로 하는 경우는 Order, Delivery, Report의 Query Handler를 참조
    - Query객체로 요청은 아래와 같이 요청됨. Query객체에 Query수행에 필요한 정보가 같이 들어오게 됨.
    getProductByProductIdQuery = new GetProductByProductIdQuery(orderDetail.getProductId());
    productDTO = queryGateway.query(getProductByProductIdQuery, ResponseTypes.instanceOf(ProductDTO.class)).join();
*/
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.example.common.dto.ProductDTO;
import org.example.common.queries.GetProductByProductIdQuery;
import org.example.productservice.entity.Product;
import org.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ProductQueryHandler {

    private final ProductRepository productRepository;
    @Autowired
    public ProductQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    private ProductDTO handle(GetProductByProductIdQuery query) {
        log.info("[@QueryHandler] Handle <GetProductByProductIdQuery> for Product Id: {}", query.getProductId());

        Optional <Product> optProduct = productRepository.findById(query.getProductId());
        if(optProduct.isPresent()) {
            Product product = optProduct.get();
            return new ProductDTO(
                    product.getProductId(), product.getProductName(),
                    product.getUnitPrice(), product.getProductQty());
        } else {
            return null;
        }
    }
}
