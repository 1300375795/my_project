package com.ydg.myproject.controller;

import com.ydg.myproject.common.RestResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ydg
 * @Company qcsz
 * @date 2018-12-17
 * @description
 */
@Slf4j
@RestController
@Api(value = "login", description = "登录controller")
public class LoginController {

    @PostMapping("/login")
    public RestResponse<String> login() {
        log.info("===============**进入登录的controller**================");
        return RestResponse.success("success");
    }

}
