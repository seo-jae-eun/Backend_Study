package com.example.day05.product;

import com.example.day05.product.model.Product;
import com.example.day05.product.model.ProductImage;
import com.example.day05.product.model.request.ProductCreateReq;
import com.example.day05.product.model.response.ProductCreateRes;
import com.example.day05.product.model.response.ProductReadRes;
import com.example.day05.seller.model.Seller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductImageRepository productImageRepository;

    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    public List<ProductReadRes> list() {
        List<Product> productList = productRepository.findAll();

        List<ProductReadRes> productReadResList = new ArrayList<ProductReadRes>();

        for (Product product : productList) {
            ProductReadRes productReadRes = product.toDto();
            productReadResList.add(productReadRes);

        }
        return productReadResList;
    }

    public ProductCreateRes create(ProductCreateReq dto, String fileName) {
        Seller seller = new Seller();
        seller.setId(1L);

        Product product = dto.toEntity(seller);
        productRepository.save(product);


//        ProductImage productImage = new ProductImage();
//        productImage.setImageUrl(fileName);
//        productImage.setProduct(product);
        ProductImage productImage = ProductImage.builder()
                        .imageUrl(fileName)
                        .product(product).build();
        productImageRepository.save(productImage);
        if (product.getId() != null) {
            return new ProductCreateRes("성공");
        }

        return new ProductCreateRes("실패");
    }

    public ProductReadRes detail(Long id) {
        Optional<Product> result = productRepository.findById(id);
        Product product = result.get();

        ProductReadRes productReadRes = product.toDto();
        return productReadRes;
    }
}
