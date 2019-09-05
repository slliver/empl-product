package com.zhongwang.support.exception;

/**
 * @Description: session超时异常
 * @author: slliver
 * @date: 2019/3/7 13:30
 * @version: 1.0
 */
public class ZWSessionTimeoutException extends ZWException {

    private static final long serialVersionUID = -9075750899343599790L;

    public ZWSessionTimeoutException(String message) {
        super(message);
    }

    public ZWSessionTimeoutException(int category, String message) {
        super(category, message);
    }

    public ZWSessionTimeoutException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ZWSessionTimeoutException(int category, String message, Throwable throwable) {
        super(category, message, throwable);
    }

    public int getCategory() {
        return this.category;
    }

    public String getMessage() {
        return this.message;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }
}
