package com.example.day04.product;

import com.example.day04.Seller;
import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Integer productPrice;
    private Integer deliveryPrice;
    private Integer addDeliveryPrice;
    private Integer outboundDays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToOne(mappedBy = "product")
    private ProductImage productImage;


    public Product() {
    }

    public Product(String productName, Integer productPrice, Integer deliveryPrice, Integer addDeliveryPrice, Integer outboundDays) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.deliveryPrice = deliveryPrice;
        this.addDeliveryPrice = addDeliveryPrice;
        this.outboundDays = outboundDays;
    }

    public Product(String productName, Integer productPrice, Integer deliveryPrice, Integer addDeliveryPrice, Integer outboundDays, Seller seller) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.deliveryPrice = deliveryPrice;
        this.addDeliveryPrice = addDeliveryPrice;
        this.outboundDays = outboundDays;
        this.seller = seller;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Integer deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public Integer getAddDeliveryPrice() {
        return addDeliveryPrice;
    }

    public void setAddDeliveryPrice(Integer addDeliveryPrice) {
        this.addDeliveryPrice = addDeliveryPrice;
    }

    public Integer getOutboundDays() {
        return outboundDays;
    }

    public void setOutboundDays(Integer outboundDays) {
        this.outboundDays = outboundDays;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }
}
