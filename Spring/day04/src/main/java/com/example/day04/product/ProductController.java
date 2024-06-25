package com.example.day04.product;

import com.example.day04.file.FileUploadService;
import com.example.day04.product.model.ProductCreateReq;
import com.example.day04.product.model.ProductCreateRes;
import com.example.day04.product.model.ProductDetailRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final FileUploadService fileUploadService;

    public ProductController(ProductService productService, FileUploadService fileUploadService) {
        this.productService = productService;
        this.fileUploadService = fileUploadService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<ProductCreateRes> create(@RequestPart ProductCreateReq dto, @RequestPart MultipartFile file) {
        String fileName = fileUploadService.upload(file);

        ProductCreateRes productCreateRes = productService.create(dto, fileName);
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
