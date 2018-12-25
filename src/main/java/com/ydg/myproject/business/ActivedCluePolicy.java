package com.ydg.myproject.business;

import com.ydg.myproject.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * 激活线索的政策
 */
@Service
public class ActivedCluePolicy extends AbstractLeadLoadPolicy {

    @Override
    public boolean load(SysUser lead, LeadLoadPolicyContext context) {
        return false;
    }

    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 3;
    }
}
