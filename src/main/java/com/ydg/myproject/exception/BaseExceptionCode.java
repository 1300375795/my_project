package com.ydg.myproject.exception;

/**
 * @author ydg
 * @date 2018/8/10
 * @description
 */
public interface BaseExceptionCode {
    /**
     * 获取状态吗
     *
     * @return
     */
    int getCode();

    /**
     * 获取状态信息
     *
     * @return
     */
    String getMsg();
}
