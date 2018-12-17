package com.ydg.myproject.controller;

import com.ydg.myproject.common.BaseController;
import com.ydg.myproject.common.RestResponse;
import com.ydg.myproject.token.TokenServer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LoginController extends BaseController {
    @Autowired
    private TokenServer tokenServer;

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    public RestResponse<String> login() {
        log.info("===============**进入登录的controller**================");
        String token = tokenServer.generateToken("yedeguo");
        return RestResponse.success(token);
    }

    @PostMapping("/logout")
    public RestResponse<String> logout() {
        log.info("===============**进入退出登录的controller**================");
        return RestResponse.success("success");
    }

}
