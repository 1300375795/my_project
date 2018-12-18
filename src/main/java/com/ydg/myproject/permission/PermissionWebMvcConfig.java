package com.ydg.myproject.permission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器注入
 *
 * @author ydg
 * @Company qcsz
 * @date 2018-12-18
 * @description
 */
@Slf4j
@Configuration
public class PermissionWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private PermissionInterceptor permissionInterceptor;

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("进入注入拦截器的实现");
        registry.addInterceptor(permissionInterceptor).addPathPatterns("/**");
    }
}
