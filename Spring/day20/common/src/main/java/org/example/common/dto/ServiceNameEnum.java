package org.example.common.dto;

public enum ServiceNameEnum {
    ORDER("order"),
    PAYMENT("payment"),
    DELIVERY("delivery"),
    PRODUCT("product"),
    REPORT("report");

    private final String value;
    ServiceNameEnum(String value) {
        this.value = value;
    }
    public String value() {
        return value;
    }
}
