package com.ydg.myproject.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author ydg
 * @Company qcsz
 * @date 2018-12-21
 * @description 测试@Scope("prototype")的作用
 * 他的作用就是每个地方注入的bean都是不一样的，不是单例形式存在的
 */
@Slf4j
@Component
@Scope("prototype")
public class TestScope {

    /**
     * 输出当前对象的一些信息日志
     */
    public void logThis() {
        log.info("当前对象是：", this);
        log.info("当前的hashCode是：", this.hashCode());
        log.info("当前的toString是：", this.toString());
    }

}
