package com.ydg.myproject.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ydg.myproject.entity.SysUser;
import com.ydg.myproject.mapper.ISysUserMapper;
import com.ydg.myproject.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ydg
 * @Company qcsz
 * @date 2018-12-16
 * @description 用户服务实现类
 */
@Slf4j
@Service
public class ISysUserServiceImpl extends ServiceImpl<ISysUserMapper, SysUser> implements ISysUserService {
    /**
     * 保存用户信息
     *
     * @param sysUser
     * @return
     */
    @Override
    public SysUser save(SysUser sysUser) {
        log.info("进入保存一个用户信息的service");
        this.insert(sysUser);
        return sysUser;
    }
}
