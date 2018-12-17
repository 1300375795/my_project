package com.ydg.myproject.permission;

import com.alibaba.fastjson.JSON;
import com.ydg.myproject.common.BaseController;
import com.ydg.myproject.common.RestResponse;
import com.ydg.myproject.entity.SysUser;
import com.ydg.myproject.exception.BizException;
import com.ydg.myproject.exception.code.ExceptionCode;
import com.ydg.myproject.token.TokenServer;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ydg
 * @date 2018/12/16
 */
@Slf4j
@Component
public class PermissionInterceptor implements HandlerInterceptor {
    public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
    @Autowired
    private TokenServer tokenServer;
    //权限检验开关,一般情况下不允许修改，只用于开发内部为调试方便而设置
    //@Value("${security.com.ycd.common.auth.permission.check.flag:true}")
    //@Setter
    private boolean permissionCheckFlag = true;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO: 2018-12-17 这个拦截器没有进来,需要看下是什么问题
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        log.info("PermissionInterceptor.preHandle(). request uri = {}, method = {}", request.getRequestURI(),
                request.getMethod());
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        log.info("handlerClass = {}, handlerMethod = {}", handlerMethod.getBean().getClass().getName(),
                handlerMethod.getMethod().getName());

        if (!(handlerMethod.getBean() instanceof BaseController)) {
            return true;
        }

        if (handlerMethod.hasMethodAnnotation(DisableLoginVerify.class)) {
            return true;
        }

        PermissionConfig permissionConfig = handlerMethod.getMethodAnnotation(PermissionConfig.class);
        String[] codes = getPermissionCodes(permissionConfig);

        Map<String, Object> map = tokenServer.validateToken(request);
        SysUser sysUser = JSON.parseObject(JSON.toJSONString(map), SysUser.class);
        log.info("根据token解析出来的user是：{}", sysUser);
        if (sysUser == null) {
            log.error("PermissionInterceptor.verify, user do not login.");
            response.setContentType(CONTENT_TYPE_JSON);
            response.getWriter().write(createErrorResp(ExceptionCode.NOT_LOGIN));
            response.getWriter().flush();
            return false;
        }

        if (!permissionCheckFlag || codes == null) {
            return true;
        }

        if (permissionVerify(codes, sysUser)) {
            PermissionContextUtil.setCurrentCodes(Arrays.asList(codes));
            return true;
        } else {
            String requestInfo = getRequestInfo(handlerMethod);
            log.warn(requestInfo);
            response.setContentType(CONTENT_TYPE_JSON);
            response.getWriter().write(createErrorResp(ExceptionCode.PERMISSION_DENIED));
            response.getWriter().flush();
            return false;
        }
    }

    private String[] getPermissionCodes(PermissionConfig permissionConfig) {
        if (permissionConfig == null) {
            return null;
        }

        if (ArrayUtils.isNotEmpty(permissionConfig.codes())) {
            return permissionConfig.codes();
        }

        return null;
    }

    private boolean permissionVerify(String[] codes, SysUser sysUser) {
        if (codes == null) {
            return true;
        }

        for (String code : codes) {
            // TODO: 2018-12-17 可以改成权限code码
        }
        return false;
    }

    public String createErrorResp(ExceptionCode exceptionCode) {
        RestResponse<Object> restResponse = RestResponse
                .fail(new BizException(exceptionCode.getCode(), exceptionCode.getMsg()));
        String errorResp = JSON.toJSONString(restResponse);
        return errorResp;
    }

    private String getRequestInfo(HandlerMethod handlerMethod) {
        RequestMapping clazzMapping = handlerMethod.getBean().getClass().getAnnotation(RequestMapping.class);
        RequestMapping methodMapping = handlerMethod.getMethodAnnotation(RequestMapping.class);
        StringBuilder sb = new StringBuilder("url = ");
        if (clazzMapping != null && clazzMapping.value().length > 0) {
            sb.append(clazzMapping.value()[0]);
        }
        if (methodMapping != null) {
            sb.append(methodMapping.value()[0]);
            String method = "";
            if (methodMapping.method() == null || methodMapping.method().length == 0) {
                method = "get";
            } else {
                method = methodMapping.method()[0].toString();
            }
            sb.append(" , method = " + method);
        }
        return sb.toString();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //log.info("PermissionInterceptor.afterCompletion()...");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
            throws Exception {
        //log.info("PermissionInterceptor.postHandle()...");
    }

}
