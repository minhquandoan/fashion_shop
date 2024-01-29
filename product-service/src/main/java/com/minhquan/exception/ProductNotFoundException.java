package com.minhquan.exception;

import com.minhquan.constant.exception.ErrorCode;

import lombok.ToString;

@ToString
public class ProductNotFoundException extends CommonException {
    public ProductNotFoundException() {
        super(ErrorCode.PRODUCT_NOT_FOUND, "ProductNotFound");
    }
}
