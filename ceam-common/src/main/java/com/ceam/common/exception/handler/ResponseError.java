package com.ceam.common.exception.handler;

import cn.hutool.http.HttpStatus;
import lombok.Data;

/**
 * @author CeaM
 * 2023/01/27 10:53
 **/
@Data
public class ResponseError {

    private Integer code  = HttpStatus.HTTP_BAD_REQUEST;
    private String message;

    public static ResponseError error(String msg) {
        ResponseError responseError = new ResponseError();
        responseError.message = msg;
        return responseError;
    }

    public static ResponseError error(Integer status, String msg) {
        ResponseError responseError = new ResponseError();
        responseError.code = status;
        responseError.message = msg;
        return responseError;
    }

}
