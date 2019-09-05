package com.zhongwang.support.exception;

/**
 * @Description: 初始化异常定义，比如树形节点构造失败等
 * @author: slliver
 * @date: 2019/3/7 15:53
 * @version: 1.0
 */
public class ZWInitializationException extends ZWException {
    private static final long serialVersionUID = 572336572013182508L;

    /**
     * @param message 异常信息
     */
    public ZWInitializationException(String message) {
        super(message);
    }

    /**
     * @param category 异常类型
     * @param message  异常信息
     */
    public ZWInitializationException(int category, String message) {
        super(category, message);
    }

    /**
     * @param message   异常信息
     * @param throwable 异常对象
     */
    public ZWInitializationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * @param category  异常类型
     * @param message   异常信息
     * @param throwable 异常对象
     */
    public ZWInitializationException(int category, String message, Throwable throwable) {
        super(category, message, throwable);
    }

    /**
     * 取得异常类型
     *
     * @return 异常类型
     */
    public int getCategory() {
        return category;
    }

    /**
     * 取得异常信息
     *
     * @return 异常信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 取得异常对象
     *
     * @return 异常对象
     */
    public Throwable getThrowable() {
        return throwable;
    }
}
