package com.minhquan.exception;

import com.minhquan.constant.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class CommonException extends RuntimeException {
    private final String errCode;
    private final String message;
    private final String log;
    private Throwable rootException;

    public CommonException(ErrorCode err, String log) {
        this.errCode = err.getErrCode();
        this.message = err.getMessage();
        this.log = log;
    }
}
