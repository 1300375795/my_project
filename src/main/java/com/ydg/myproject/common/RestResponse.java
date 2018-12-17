package com.ydg.myproject.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ydg.myproject.exception.BaseExceptionCode;
import com.ydg.myproject.exception.BizException;

/**
 * @author dongzf
 * @date 2018/8/10
 * @description
 */
public class RestResponse<T> {
    public static final String DEF_ERROR_MESSAGE = "系统繁忙，请稍候再试";
    public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";
    private static final int SUCCESS = 0;
    private static final int FAIL = -1;
    /**
     * 接口返回码
     * 0：成功，-1:系统繁忙，其他详情见[ExceptionCode]
     */
    private int resultCode;
    /**
     * 结果消息，如果调用成功，消息通常为空T
     */
    private String resultMsg = "success";
    /**
     * 返回数据
     */
    private T data;


    private RestResponse() {
        super();
    }

    public RestResponse(int resultCode, T data, String resultMsg) {
        this.resultCode = resultCode;
        this.data = data;
        this.resultMsg = resultMsg;
    }

    /**
     * request success
     *
     * @param data 返回结果
     * @param <E>
     * @return
     */
    public static <E> RestResponse<E> success(E data) {
        return new RestResponse<>(SUCCESS, data, "success");
    }


    public static <E> RestResponse<E> successPage(E data) {
        RestResponse R = new RestResponse<>(SUCCESS, data, "success", true);
        return R;
    }

    public RestResponse(int resultCode, T data, String resultMsg, boolean isPage) {
        this.resultCode = resultCode;
        this.data = data;
        this.resultMsg = resultMsg;

    }

    /**
     * request success
     *
     * @param data 返回结果
     * @param msg  返回消息
     * @param <E>
     * @return
     */
    public static <E> RestResponse<E> success(E data, String msg) {
        return new RestResponse<>(SUCCESS, data, msg);
    }

    /**
     * request fail
     *
     * @param code 错误码
     * @param msg  错误消息
     * @return
     */
    public static <E> RestResponse<E> fail(int code, String msg) {
        return new RestResponse<>(code, null, (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg);
    }

    /**
     * request fail
     *
     * @param msg
     * @param <E>
     * @return
     */
    public static <E> RestResponse<E> fail(String msg) {
        return fail(FAIL, msg);
    }

    /**
     * request fail
     *
     * @param exceptionCode
     * @param <E>
     * @return
     */
    public static <E> RestResponse<E> fail(BaseExceptionCode exceptionCode) {
        return new RestResponse<>(exceptionCode.getCode(), null,
                (exceptionCode.getMsg() == null || exceptionCode.getMsg().isEmpty()) ? DEF_ERROR_MESSAGE : exceptionCode.getMsg());
    }

    public static <E> RestResponse<E> fail(BizException exception) {
        if (exception == null) {
            return fail(DEF_ERROR_MESSAGE);
        }
        return new RestResponse<>(exception.getCode(), null, exception.getMessage());
    }

    /**
     * 请求失败消息，根据异常类型，获取不同的提供消息
     *
     * @param throwable 异常
     * @return RPC调用结果
     */
    public static <E> RestResponse<E> fail(Throwable throwable) {
        return fail(throwable != null ? throwable.getMessage() : DEF_ERROR_MESSAGE);
    }

    public static <E> RestResponse<E> timeout() {
        return fail(HYSTRIX_ERROR_MESSAGE);
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

 
    /**
     * 逻辑处理是否成功
     *
     * @return 是否成功
     */
    @JsonIgnore

    public boolean isSuccess() {
        return this.resultCode == SUCCESS;
    }
}
