package com.ydg.myproject.service.impl;

import com.ydg.myproject.constant.Constant;
import com.ydg.myproject.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author ydg
 * @Company qcsz
 * @date 2018-12-20
 * @description
 */
@Slf4j
@Service
public class ThreadServiceImpl implements ThreadService {
    @Autowired
    private ThreadComponent threadComponent;

    /**
     * 启动多个线程
     */
    @Override
    public void runManyThread() {
        log.info("当前的线程是：" + Thread.currentThread().getName());
        threadComponent.thread1();
        threadComponent.thread2();
    }

    /**
     * 具体的需要执行的任务类
     * 不能this.@Async方法这样会不生效
     * 索引将@Async方法抽离到另一个类中，这样就能生效了
     */
    @Component
    class ThreadComponent {
        @Async
        public void thread1() {
            for (int i = 0; i < Constant.THREAD_RUN_TIME; i++) {
                log.info("thread1,当前执行的线程是：" + Thread.currentThread().getName() + "执行到：" + i + "条记录");
            }
        }

        @Async
        public void thread2() {
            for (int i = 0; i < Constant.THREAD_RUN_TIME; i++) {
                log.info("thread2,当前执行的线程是：" + Thread.currentThread().getName() + "执行到：" + i + "条记录");
            }
        }
    }
}