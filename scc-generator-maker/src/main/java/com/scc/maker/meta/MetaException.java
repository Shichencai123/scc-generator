package com.scc.maker.meta;

/**
 * @Date: 2025/1/24 15:15
 * @Author: shicc
 * @Description: 元信息异常
 */
public class MetaException extends RuntimeException {

    public MetaException(String message) {
        super(message);
    }

    public MetaException(String message, Throwable cause) {
        super(message, cause);
    }
}
