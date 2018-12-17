package com.ydg.myproject.config;

import com.ydg.myproject.exception.BizException;
import com.ydg.myproject.exception.code.ExceptionCode;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author dongzf
 * @date 2018/8/23
 * @description
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException)
            throws IOException, ServletException {
        PrintOutException.printException(res, new BizException(ExceptionCode.REQUEST_INVALID_NOT_CORRECT.getCode(),
                ExceptionCode.REQUEST_INVALID_NOT_CORRECT.getMsg()));
    }
}
