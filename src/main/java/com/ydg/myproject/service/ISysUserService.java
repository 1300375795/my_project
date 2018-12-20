package com.ydg.myproject.service;

import com.baomidou.mybatisplus.service.IService;
import com.ydg.myproject.entity.SysUser;

/**
 * <p>
 * 用户服务类
 * </p>
 *
 * @author ydg
 * @since 2018-12-16
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 保存用户信息
     *
     * @param sysUser
     * @return
     */
    SysUser save(SysUser sysUser);

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    SysUser getById(String id);
}
