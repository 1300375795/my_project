package com.ydg.myproject.controller;

import com.ydg.myproject.annotation.custom.Auth;
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
@Api(value = "auth", description = "权限校验测试的controller")
public class AuthController implements com.ydg.myproject.common.Auth {

    @Auth
    @PostMapping("test")
    public RestResponse<String> testAuth() {
        log.info("===============**进入需要权限验证的controller**================");
        return RestResponse.success("success");
    }
}
