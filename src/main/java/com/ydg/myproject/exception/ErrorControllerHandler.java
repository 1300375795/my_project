package com.ydg.myproject.exception;

import com.ydg.myproject.common.RestResponse;
import com.ydg.myproject.exception.code.ExceptionCode;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dongzf
 * @date 2018/8/23
 * @description 原本捕获500 和404 ，500由全局捕获，这里处理404
 */
@RestController
public class ErrorControllerHandler implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public RestResponse handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 400) {
            return new RestResponse(ExceptionCode.BAD_REQUEST.getCode(), null, ExceptionCode.BAD_REQUEST.getMsg());
        } else if (statusCode == 401) {
            return new RestResponse(ExceptionCode.UNAUTHORIZED.getCode(), null, ExceptionCode.UNAUTHORIZED.getMsg());
        } else if (statusCode == 403) {
            return new RestResponse(ExceptionCode.FORBIDDEN.getCode(), null, ExceptionCode.FORBIDDEN.getMsg());
        } else if (statusCode == 404) {
            return new RestResponse(ExceptionCode.NOT_FOUND.getCode(), null, ExceptionCode.NOT_FOUND.getMsg());
        } else {
            Throwable t = (Throwable) request.getAttribute("javax.servlet.error.exception");
            return new RestResponse(statusCode, null, "mainsiteerror=====" + t.getMessage());
        }
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}

