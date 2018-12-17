package com.ydg.myproject.controller;

import com.ydg.myproject.annotation.bean.TestBean;
import com.ydg.myproject.common.BaseController;
import com.ydg.myproject.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yedeguo
 * @Company qcsz
 * @date 2018-12-15
 * @description
 */
@Slf4j
@RestController
@Api(value = "/bean", description = "bean注解的测试")
public class BeanController extends BaseController {
    @Autowired
    private TestBean testBean;

    @ApiOperation(value = "简单测试下", notes = "测试bean注解的注入情况")
    @PostMapping(value = "test")
    public void testBean(@RequestBody SysUser sysUser) {
        log.info("===============****================");
        log.info("进入的参数为：{}", sysUser);
        testBean.testA();
        log.info("===============****================");
    }
}
