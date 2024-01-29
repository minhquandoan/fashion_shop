package com.minhquan.constant.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // Common Exception
    COMMON_ERROR("Error was thrown with your request", "001"),

    // Product Service
    PRODUCT_NOT_FOUND("Could not found this product", "P_001"),
    PRODUCT_UPDATE_FAILED("Could not update new data", "P_1001")
    ;

    final String message;
    final String errCode;
}
