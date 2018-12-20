package com.ydg.myproject.service.impl;

import com.ydg.myproject.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
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

    @Async
    void thread1() {
        log.info("thread1,当前执行的线程是：" + Thread.currentThread().getName());
        for (int i = 0; i < 100; i++) {
            log.info("第一个线程执行到：" + i + "条记录");
        }
    }

    @Async
    void thread2() {
        log.info("thread2,当前执行的线程是：" + Thread.currentThread().getName());
        for (int i = 0; i < 100; i++) {
            log.info("第二个线程执行到：" + i + "条记录");
        }
    }

    /**
     * 启动多个线程
     */
    @Override
    public void runManyThread() {
        thread1();
        thread2();
    }
}
