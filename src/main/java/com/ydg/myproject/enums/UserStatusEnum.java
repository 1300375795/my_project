package com.ydg.myproject.enums;

import lombok.Getter;

/**
 * @author ydg
 * @Company qcsz
 * @date 2018-12-20
 * @description
 */
@Getter
public enum UserStatusEnum {

    /**
     * 正常
     */
    NORMAL(0, "正常"),

    /**
     * 注销
     */
    LOGOUT(1, "注销"),

    ;

    private Integer status;
    private String message;

    UserStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }}
