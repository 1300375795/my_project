package com.ydg.myproject.exception;

import com.ydg.myproject.common.RestResponse;
import com.ydg.myproject.exception.code.ExceptionCode;
import java.nio.file.AccessDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理类
 *
 * @author ydg
 * @date 2018/8/10
 * @description
 */
@ControllerAdvice(value = { "com.ydg.myproject" })
@ResponseBody
public class GlobalExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BizException.class)
    public RestResponse baseExceptionHandler(BizException ex) {
        log.error("BizException:", ex);
        return new RestResponse(ex.getCode(), null, ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public RestResponse processAccessDeniedException(AccessDeniedException e) {
        return new RestResponse(ExceptionCode.ERR_ACCESS_DENIED.getCode(), null, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public RestResponse otherExceptionHandler(Exception ex) {
        log.error("Exception:", ex);
        return new RestResponse(ExceptionCode.SYSTEM_BUSY.getCode(), null, ex.getMessage());
    }

    /**
     * @param e
     * @return
     * @throws BindException
     */
    @ExceptionHandler(value = BindException.class)
    public RestResponse handleBindException(BindException e) throws BindException {
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        FieldError fieldError = e.getFieldError();
        StringBuilder sb = new StringBuilder();
        sb.append(fieldError.getField()).append("=[").append(fieldError.getRejectedValue()).append("]")
                .append(fieldError.getDefaultMessage());
        return RestResponse.fail(sb.toString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResponse illegalArgumentException(IllegalArgumentException e) {
        log.error("参数非法异常={}", e.getMessage(), e);
        return RestResponse.fail("参数非法异常=" + e.getMessage());
    }
}
