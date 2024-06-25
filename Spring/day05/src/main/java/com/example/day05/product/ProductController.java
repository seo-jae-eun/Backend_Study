package com.example.day05.product;

import com.example.day05.file.FileUploadService;
import com.example.day05.product.model.request.ProductCreateReq;
import com.example.day05.product.model.response.ProductCreateRes;
import com.example.day05.product.model.response.ProductReadRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<ProductReadRes>> list() {
        List<ProductReadRes> response = productService.list();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    public ResponseEntity<ProductReadRes> detail(Long id) {
        ProductReadRes response = productService.detail(id);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<ProductCreateRes> create(@RequestPart ProductCreateReq dto, @RequestPart MultipartFile file){
        String fileName = fileUploadService.upload(file);
        ProductCreateRes response = productService.create(dto, fileName);
        return ResponseEntity.ok(response);
    }
}
