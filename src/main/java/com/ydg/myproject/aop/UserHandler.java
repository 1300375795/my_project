package com.ydg.myproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author ydg
 * @date 2018/9/3
 * @description
 */
@Slf4j
@Aspect
@Component
public class UserHandler {

    @Pointcut("execution(* com.ydg.myproject.service.impl.ISysUserServiceImpl.*(..))")
    public void pointCutSysUser() {
    }

    /**
     * before是在around之后执行的
     *
     * @throws Throwable
     */
    @Before(value = "execution(* com.ydg.myproject.service.impl.ISysUserServiceImpl.*(..))")
    public void setHeader() throws Throwable {
        log.info("before");
    }

    /**
     * 拦截器具体实现
     *
     * @param proceedingJoinPoint 切点 切点方法的return
     * @return R  结果包装
     */
    @Around("pointCutSysUser()")
    public Object methodRHandler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("===============****================");
        log.info("进入环绕");
        return methodHandler(proceedingJoinPoint);
    }

    private Object methodHandler(ProceedingJoinPoint pjp) throws Throwable {
        log.info("出了环绕");
        Object obj = pjp.proceed();
        return obj;
    }
}
