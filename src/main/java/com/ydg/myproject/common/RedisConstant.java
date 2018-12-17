package com.ydg.myproject.common;

/**
 * @author ydg
 * @date 2018/8/24
 * @description
 */
public class RedisConstant {
    public static final String REDIS_TOKEN = "oauth.token.";
    public static final String REDIS_AUTH = "oauth.auth.";
    public final static String TOKEN_PREFIX="Bearer ";
    public static final String HEADER_STRING = "Authorization";

    /** 临时存在ThreadLocal中的 adminId key */
    public static final String CONTEXT_KEY_ACCOUNT_ID = "currentAdminId";
    /** 临时存在ThreadLocal中的 登录名 key */
    public static final String CONTEXT_KEY_ACCOUNT = "currentUserName";
}
