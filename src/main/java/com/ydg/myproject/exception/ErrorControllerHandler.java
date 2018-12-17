package com.ydg.myproject.exception;

import com.ydg.myproject.exception.code.ExceptionCode;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ydg
 * @date 2018/8/23
 * @description 原本捕获500 和404 ，500由全局捕获，这里处理404
 */
@RestController
public class ErrorControllerHandler implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public void handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == HttpStatus.BAD_REQUEST.value()) {
            throw new BaseUncheckedException(ExceptionCode.BAD_REQUEST.getCode(), ExceptionCode.BAD_REQUEST.getMsg());
        } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
            throw new BaseUncheckedException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg());
        } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
            throw new BaseUncheckedException(ExceptionCode.FORBIDDEN.getCode(), ExceptionCode.FORBIDDEN.getMsg());
        } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
            throw new BaseUncheckedException(ExceptionCode.NOT_FOUND.getCode(), ExceptionCode.NOT_FOUND.getMsg());
        } else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
            throw new BaseUncheckedException(ExceptionCode.METHOD_NOT_ALLOWED.getCode(),
                    ExceptionCode.METHOD_NOT_ALLOWED.getMsg());
        } else {
            try {
                Throwable t = (Throwable) request.getAttribute("javax.servlet.error.exception");
                throw new BaseUncheckedException(statusCode, t.getMessage());
            } catch (Exception e) {
                //如果上面的这个出现异常进一步进行处理异常，转换成未知异常
                throw new BaseUncheckedException(ExceptionCode.UNKNOWN.getCode(), ExceptionCode.UNKNOWN.getMsg());
            }
        }
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}

