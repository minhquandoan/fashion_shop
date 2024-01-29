package com.minhquan.exception.handler;

import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import com.minhquan.constant.exception.ErrorCode;
import com.minhquan.exception.CommonException;
import com.minhquan.model.error.ErrorResponse;

import jakarta.ws.rs.core.Response;

public class GlobalExceptionHandler {

    @ServerExceptionMapper(value = CommonException.class)
    public Response handleCommonException(CommonException exception) {
        return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(ErrorResponse.builder()
                                            .id(ErrorCode.COMMON_ERROR.getErrCode())
                                            .exception(exception))
                        .build();
    }
}
