package com.ydg.myproject.controller;

import com.ydg.myproject.permission.DisableLoginVerify;
import com.ydg.myproject.service.ThreadService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ydg
 * @Company qcsz
 * @date 2018-12-20
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/thread")
@Api(value = "/thread", description = "线程相关controller")
public class ThreadController {

    @Autowired
    private ThreadService threadService;

    /**
     * 运行多个线程
     */
    @DisableLoginVerify
    @PostMapping("runMany")
    public void runManyThread() {
        System.out.println("ThreadName:" + Thread.currentThread().getName());
        System.out.println("当前线程开始执行测试函数......");
        threadService.runManyThread();
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + " ");
            if (i % 10 == 0) {
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("当前线程测试函数执行完毕......");
    }
}
