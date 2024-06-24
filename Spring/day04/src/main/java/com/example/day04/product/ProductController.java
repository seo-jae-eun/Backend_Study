package com.example.day04.product;

import com.example.day04.product.model.ProductCreateReq;
import com.example.day04.product.model.ProductCreateRes;
import com.example.day04.product.model.ProductDetailRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<ProductCreateRes> create(@RequestBody ProductCreateReq productCreateReq) {
        ProductCreateRes productCreateRes = productService.create(productCreateReq);
        return ResponseEntity.ok(productCreateRes);
    }

    @RequestMapping("/detail")
    public ResponseEntity<ProductDetailRes> detail(Long id) {
        ProductDetailRes productDetailRes = productService.detail(id);
        return ResponseEntity.ok(productDetailRes);
    }

    @RequestMapping("/list")
    public ResponseEntity<List<ProductDetailRes>> list() {
        List<ProductDetailRes> productDetailResList = productService.list();
        return ResponseEntity.ok(productDetailResList);
    }
}
