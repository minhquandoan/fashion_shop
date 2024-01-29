package com.minhquan.model.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String id;
    private RuntimeException exception;
}
