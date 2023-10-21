package com.ceam.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author CeaM
 * 2023/01/19 21:05
 **/
@Getter
public class ServiceException extends RuntimeException {

    private Integer code = BAD_REQUEST.value();

    public ServiceException(String mssage) {
        super(mssage);
    }

    public ServiceException(HttpStatus code, String mssage) {
        super(mssage);
        this.code = code.value();
    }
}
