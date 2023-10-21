package com.ceam.common.exception.handler;

import com.ceam.common.exception.ServiceException;
import com.ceam.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author CeaM
 * 2023/01/27 10:06
 **/
@Slf4j
@RestControllerAdvice
@SuppressWarnings("unchecked")
public class GlobalExceptionHandler {

    /**
     * 系统未知异常
     */
    @ExceptionHandler(value = Throwable.class)
    public R<ResponseError> badRequestException(Throwable e) {
        return buildResponse("系统内部异常", e, ResponseError.error(e.getMessage()));
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = ServiceException.class)
    public R<ResponseError> serviceException(ServiceException e) {
        return buildResponse("业务异常", e, ResponseError.error(e.getCode(), e.getMessage()));
    }

    private R<ResponseError> buildResponse(String message, Throwable e,
                                           ResponseError responseError) {
        log.error(message + "：{}", e.getMessage(), e);
        return R.fail(responseError.getMessage(), responseError.getCode());
    }
}
