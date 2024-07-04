package com.example.day12.product;

import com.example.day12.product.model.Product;
import com.example.day12.product.model.request.ProductCreateReq;
import com.example.day12.product.model.response.ProductRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
//@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<ProductRes> create(@RequestBody ProductCreateReq productCreateReq) {
        return ResponseEntity.ok(productService.create(productCreateReq));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity<ProductRes> read(Long id) {
        return ResponseEntity.ok(productService.read(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<ProductRes>> list() {
        return ResponseEntity.ok(productService.list());
    }
}
