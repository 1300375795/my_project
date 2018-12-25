package com.ydg.myproject.business;

import com.ydg.myproject.entity.SysUser;

public interface ILeadLoadPolicy {

    public boolean load(SysUser sysUser, LeadLoadPolicyContext context);

    public int getOrder();
}
