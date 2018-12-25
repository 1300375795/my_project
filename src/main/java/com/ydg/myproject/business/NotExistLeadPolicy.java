package com.ydg.myproject.business;

import com.ydg.myproject.entity.SysUser;
import org.springframework.stereotype.Service;

@Service
public class NotExistLeadPolicy extends AbstractLeadLoadPolicy {

    @Override
    public boolean load(SysUser sysUser, LeadLoadPolicyContext context) {
        return false;
    }

    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 6;
    }
}
