package com.ydg.myproject.aop;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

/**
 * @author ydg
 * @Company qcsz
 * @date 2018-12-15
 * @description
 */
@Component
public class LogQueue {
    private BlockingQueue<Object> logQueue = new LinkedBlockingQueue<>();

    public void add(Object log) {
        logQueue.add(log);
    }

    public Object poll() throws InterruptedException {
        return logQueue.poll(5, TimeUnit.SECONDS);
    }

}
