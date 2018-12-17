package com.ydg.myproject.entity;

import com.ydg.myproject.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ydg
 * @since 2018-12-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String account;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机
     */
    private String tel;
    /**
     * 盐
     */
    private String salt;
    /**
     * 用户名
     */
    private String username;


}
