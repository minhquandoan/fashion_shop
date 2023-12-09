package com.minhquan.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Variant {
    private Long erpId;

    private String sku;

    private BigDecimal price;

    private int quantity;
}
