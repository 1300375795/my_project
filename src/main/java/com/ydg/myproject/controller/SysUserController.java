package com.ydg.myproject.controller;

import com.ydg.myproject.common.BaseController;
import com.ydg.myproject.common.RestResponse;
import com.ydg.myproject.entity.SysUser;
import com.ydg.myproject.enums.UserStatusEnum;
import com.ydg.myproject.permission.DisableLoginVerify;
import com.ydg.myproject.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户前端控制器
 * </p>
 *
 * @author ydg
 * @since 2018-12-16
 */
@Slf4j
@RestController
@RequestMapping("/sysUser")
@Api(value = "/sysUser", description = "用户controller")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService userService;

    /**
     * 保存一个用户的具体信息
     *
     * @param sysUser
     * @return
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "保存一个用户信息", notes = "保存一个用户的具体信息")
    public RestResponse<SysUser> save(@RequestBody SysUser sysUser) throws Throwable {
        log.info("===============**进入保存一个用户的controller**================");
        log.info("给出的参数是：{}", sysUser);
        sysUser.setId(null);
        sysUser.baseSave();
        sysUser.setUserStatus(UserStatusEnum.NORMAL);
        SysUser save = userService.save(sysUser);
        log.info("保存的结果是：{}", save.getId());
        return RestResponse.success(sysUser);
    }

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("get/{id}")
    public RestResponse<SysUser> getById(@PathVariable("id") String id) {
        SysUser sysUser = userService.getById(id);
        log.info("保存的结果是：{}", sysUser);
        return RestResponse.success(sysUser);
    }

    @DisableLoginVerify
    @PostMapping("update/{appId}/info")
    public void update(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String requestURI = request.getRequestURI();
        System.out.println("uri：" + requestURI);
        System.out.println("url：" + requestURL);
        System.out.println("appId为：" + null);
    }
}

