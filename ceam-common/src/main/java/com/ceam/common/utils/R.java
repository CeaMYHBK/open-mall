package com.ceam.common.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author CeaM
 * 2023/04/18 20:17
 **/
@Data
public class R<T> implements Serializable {

    private T result;
    private String message;
    private Integer code;

    public R() {}

    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setCode(HttpStatus.OK.value());
        r.setResult(data);
        r.setMessage("OK");
        return r;
    }

    public static <T> R<T> ok() {
        R<T> r = new R<>();
        r.setCode(HttpStatus.OK.value());
        r.setMessage("OK");
        return r;
    }

    public static <T> R<T> fail(String message, int code) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }
}
