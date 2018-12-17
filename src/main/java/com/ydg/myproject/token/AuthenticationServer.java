package com.ydg.myproject.token;

import com.ydg.myproject.exception.BizException;
import com.ydg.myproject.exception.code.ExceptionCode;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author ydg
 * @date 2018/7/20
 * @description
 */
public class AuthenticationServer extends OncePerRequestFilter {
    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();
    Logger log = LoggerFactory.getLogger(AuthenticationServer.class);
    private String protectedUrl;
    private TokenServer tokenServer;
    private RedisTemplate redisConfig;

    public AuthenticationServer(String protectedUrl, TokenServer tokenServer, RedisTemplate redisTemplate) {
        this.protectedUrl = protectedUrl;
        this.tokenServer = tokenServer;
        this.redisConfig = redisConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, BizException {
        try {
            if (isProtectedUrl(request)) {
                // TODO: 2018-12-17 还可以做一些token处理，暂时不做
                throw new BizException(ExceptionCode.TOKEN_EXPIRED.getCode(), ExceptionCode.TOKEN_EXPIRED.getMsg());
            }
            filterChain.doFilter(request, response);
        } catch (BizException e) {
            log.debug(e.getMessage());
            throw new BizException(ExceptionCode.SYSTEM_BUSY.getCode(), ExceptionCode.SYSTEM_BUSY.getMsg());
        }

    }

    private boolean isAuth(HttpServletRequest request, String account) {
       /* if("GET".equals(request.getMethod())){
            return true;
        }
        if(redisConfig.redisTemplate().hasKey(RedisConstant.REDIS_AUTH+account+"."+request.getServletPath()+"."+request.getMethod())) {
            return true;
        }
        return false;*/
        return true;
    }

    private boolean isProtectedUrl(HttpServletRequest request) {
        String[] paths = protectedUrl.split(";");
        boolean isProtectedUrl = false;
        String servletPath = request.getServletPath();
        log.info("实际的路径是：{}", servletPath);
        for (String path : paths) {
            log.info("受保护的地址是：{}", path);
            if (PATH_MATCHER.match(path, servletPath)) {
                isProtectedUrl = true;
                break;
            }
        }
        return isProtectedUrl;
    }
}
