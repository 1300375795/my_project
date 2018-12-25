package com.ydg.myproject.business;

import com.ydg.myproject.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * 默认的加载策略
 *
 * @author yedeguo
 */
@Service
public class DefaultLoadLeadPolicy extends AbstractLeadLoadPolicy {

    @Override
    public boolean load(SysUser sysUser, LeadLoadPolicyContext context) {
        return true;

    }

    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 9;
    }
}
