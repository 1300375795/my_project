package com.ydg.myproject.exception;

/**
 * @author ydg
 * @date 2018/8/10
 * @description
 */
public class BizException extends BaseUncheckedException {

    private static final long serialVersionUID = -3843907364558373817L;

    public BizException(int code, String message) {
        super(code, message);
    }

    public BizException(int code, String format, Object... args) {
        super(code, String.format(format, args));
        this.code = code;
        this.message = String.format(format, args);
    }

    /**
     * 实例化异常
     * @param code
     * @param message
     * @param args
     * @return
     */
    public static BizException wrap(int code, String message, Object... args) {
        return new BizException(code, message, args);
    }

    @Override
    public String toString() {
        return "BizException [message=" + message + ", code=" + code + "]";
    }

}
