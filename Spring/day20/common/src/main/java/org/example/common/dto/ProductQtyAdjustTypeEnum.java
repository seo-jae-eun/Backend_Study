package org.example.common.dto;

public enum ProductQtyAdjustTypeEnum {
    INCREASE("+"),
    DECREASE("-");

    private String value;

    ProductQtyAdjustTypeEnum(String value) {
        this.value = value;
    }
    public String value() {
        return value;
    }
}
