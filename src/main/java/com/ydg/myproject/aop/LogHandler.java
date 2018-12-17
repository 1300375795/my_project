package com.ydg.myproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author dongzf
 * @date 2018/10/8
 * @description 日志处理
 */
@Component
@Slf4j
public class LogHandler implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(LogHandler.class);
    private Thread thread;
    public static final int QUEUE_BATCH_INSERT_SIZE = 50;
    private int batchInsertSize = QUEUE_BATCH_INSERT_SIZE;
    private boolean threadActive = true;
    private LogQueue logQueue;


    @PostConstruct
    public void init() {
        log.info("开启服务器");
        log.info("日志队列是：{}", logQueue);
        thread = new Thread(this);
        thread.start();
    }

    @PreDestroy
    public void close() {
        threadActive = false;
        log.info("服务器关闭");
    }

    @Override
    public void run() {
        while (threadActive) {
            execute();
        }
    }

    public void execute() {
    }

    @Resource
    public void setLogQueue(LogQueue logQueue) {
        this.logQueue = logQueue;
    }

}
