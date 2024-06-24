package com.example.day04.product.model;

public class ProductCreateReq {
    private String productName;
    private Integer productPrice;
    private Integer deliveryPrice;
    private Integer addDeliveryPrice;
    private Integer outboundDays;

    public ProductCreateReq(String productName, Integer productPrice, Integer deliveryPrice, Integer addDeliveryPrice, Integer outboundDays) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.deliveryPrice = deliveryPrice;
        this.addDeliveryPrice = addDeliveryPrice;
        this.outboundDays = outboundDays;
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
}
