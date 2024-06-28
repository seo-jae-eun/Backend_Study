package com.example.day08.payment;

import java.util.List;
import java.util.Map;

public class PaymentProducts {
    private List<Map<String, Double>> products;

    public List<Map<String, Double>> getProducts() {
        return products;
    }

    public void setProducts(List<Map<String, Double>> products) {
        this.products = products;
    }
}
