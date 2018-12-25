package com.ydg.myproject.business;

import com.ydg.myproject.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * 清理粒子政策
 *
 * @author yedeguo
 */
@Service
public class CleanLeadPolicy implements ILeadLoadPolicy {

    @Override
    public boolean load(SysUser sysUser, LeadLoadPolicyContext context) {
        return false;
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
