package com.ydg.myproject.exception;

/**
 * @author ydg
 * @date 2018/8/10
 * @description
 */
public interface BaseException {

    /**
     * 返回异常信息
     * @return
     */
    String getMessage();

    /**
     * 返回异常编码
     * @return
     */
    int getCode();

}
