package com.ydg.myproject.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.ydg.myproject.common.BaseEntity;
import com.ydg.myproject.enums.UserStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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
@ToString(callSuper = true)
public class SysUser extends BaseEntity {

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

    /**
     * 用户状态
     */
    @TableField("user_status")
    private UserStatusEnum userStatus;
}
