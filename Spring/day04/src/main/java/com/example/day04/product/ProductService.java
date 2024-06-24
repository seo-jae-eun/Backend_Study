package com.example.day04.product;

import com.example.day04.Seller;
import com.example.day04.product.model.ProductCreateReq;
import com.example.day04.product.model.ProductCreateRes;
import com.example.day04.product.model.ProductDetailRes;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

    @Transactional
    public ProductCreateRes create(ProductCreateReq productCreateReq) {
        Seller seller = new Seller((long)1);
        Product product = new Product(productCreateReq.getProductName(), productCreateReq.getProductPrice(), productCreateReq.getDeliveryPrice(), productCreateReq.getAddDeliveryPrice(), productCreateReq.getOutboundDays(), seller);
        try {
            productRepository.save(product);
            ProductImage productImage = new ProductImage("image_" + product.getId() + ".jpeg", product);
            try {
                productImageRepository.save(productImage);
                return new ProductCreateRes("성공");
            } catch (Exception e) {
                return new ProductCreateRes("실패");
            }
        } catch (Exception e) {
            return new ProductCreateRes("실패");
        }
    }

    public ProductDetailRes detail(Long id) {
        Optional<Product> result = productRepository.findById(id);
        Product product = result.get();
        return new ProductDetailRes(product.getId(), product.getProductName(), product.getProductPrice(), product.getDeliveryPrice(), product.getAddDeliveryPrice(), product.getOutboundDays(), product.getSeller().getName());
    }

    public List<ProductDetailRes> list() {
        List<Product> results = productRepository.findAll();
        List<ProductDetailRes> productDetailResList = new ArrayList<>();
        for(Product product : results) {
            productDetailResList.add(new ProductDetailRes(product.getId(), product.getProductName(), product.getProductPrice(), product.getDeliveryPrice(), product.getAddDeliveryPrice(), product.getOutboundDays(), product.getSeller().getName()));
        }
        return productDetailResList;
    }
}

