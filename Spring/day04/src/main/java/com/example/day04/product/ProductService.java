package com.example.day04.product;

import com.example.day04.Seller;
import com.example.day04.product.model.ProductCreateReq;
import com.example.day04.product.model.ProductCreateRes;
import com.example.day04.product.model.ProductDetailRes;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

//    public String makeFolder() {
//        String uploadPath = "c:\\upload";
//        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//
//        String folderPath = date.replace("/", File.separator);
//        folderPath = uploadPath + File.separator + folderPath;
//
//        File uploadPathFolder = new File(folderPath);
//        if(uploadPathFolder.exists() == false) {
//            uploadPathFolder.mkdirs();
//        }
//
//        return folderPath;
//    }
//
//    public void saveFile(MultipartFile file) {
//        String originalName = file.getOriginalFilename();
//
//        String folderPath = makeFolder();
//
//        String saveFileNames = folderPath + File.separator + originalName;
//        File saveFile = new File(saveFileNames);
//
//        try {
//            file.transferTo(saveFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @Transactional
    public ProductCreateRes create(ProductCreateReq productCreateReq, String fileName) {
        Seller seller = new Seller((long)1);
        Product product = new Product(productCreateReq.getProductName(), productCreateReq.getProductPrice(), productCreateReq.getDeliveryPrice(), productCreateReq.getAddDeliveryPrice(), productCreateReq.getOutboundDays(), seller);
        productRepository.save(product);

//        saveFile(file);
        // save 성공여부
        if(product.getId() > 0) {
            ProductImage productImage = new ProductImage(fileName, product);
            productImageRepository.save(productImage);
            if(productImage.getId() > 0) return new ProductCreateRes("성공");
            else return new ProductCreateRes("실패");
        }
        else return new ProductCreateRes("실패");
    }

    public ProductDetailRes detail(Long id) {
        Optional<Product> result = productRepository.findById(id);
        Product product = result.get();
        return new ProductDetailRes(product.getId(), product.getProductName(), product.getProductPrice(), product.getDeliveryPrice(), product.getAddDeliveryPrice(), product.getOutboundDays(), product.getSeller().getName(), product.getProductImage().getImageUrl());
    }

    public List<ProductDetailRes> list() {
        List<Product> results = productRepository.findAll();
        List<ProductDetailRes> productDetailResList = new ArrayList<>();
        for(Product product : results) {
            productDetailResList.add(new ProductDetailRes(product.getId(), product.getProductName(), product.getProductPrice(), product.getDeliveryPrice(), product.getAddDeliveryPrice(), product.getOutboundDays(), product.getSeller().getName(), product.getProductImage().getImageUrl()));
        }
        return productDetailResList;
    }
}

