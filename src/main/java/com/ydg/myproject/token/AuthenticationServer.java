package com.ydg.myproject.token;

import com.ydg.myproject.exception.BizException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author dongzf
 * @date 2018/7/20
 * @description
 */
@Slf4j
public class AuthenticationServer extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, BizException {
        String method = request.getMethod();
        log.info("获取到的方法是：{}", method);
        filterChain.doFilter(request, response);
    }

}
