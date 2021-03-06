package com.ydg.myproject.token;

import com.ydg.myproject.exception.BizException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author ydg
 * @date 2018/7/20
 * @description
 */
@Slf4j
public class AuthenticationServer extends OncePerRequestFilter {
    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();
    private String protectedUrl;
    private TokenServer tokenServer;
    private RedisTemplate redisConfig;

    public AuthenticationServer(String protectedUrl, TokenServer tokenServer, RedisTemplate redisTemplate) {
        this.protectedUrl = protectedUrl;
        this.tokenServer = tokenServer;
        this.redisConfig = redisConfig;
    }

    /**
     * 不能在下面的这个方法（子方法）中抛出异常
     * 如果抛出的话则全局异常处理器是不能捕获异常的
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     * @throws BizException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, BizException {
        //        if (isProtectedUrl(request)) {
        //            Map<String, Object> map = tokenServer.validateToken(request);
        //            Object user = map.get("user");
        //            if (user == null) {
        //                PrintOutException.printException(response, new BizException(ExceptionCode.TOKEN_SIGNATURE.getCode(),
        //                        ExceptionCode.TOKEN_SIGNATURE.getMsg()));
        //            }
        //            filterChain.doFilter(request, response);
        //            return;
        //        }
        filterChain.doFilter(request, response);
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
        String servletPath = request.getServletPath();
        for (String path : paths) {
            if (PATH_MATCHER.match(path, servletPath)) {
                return true;
            }
        }
        return false;
    }
}
