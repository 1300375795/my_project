package com.ydg.myproject.business;

import com.ydg.myproject.entity.SysUser;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadLoadPolicyChain implements InitializingBean {

    @Autowired
    private List<ILeadLoadPolicy> policys;

    public void load(SysUser sysUser, LeadLoadPolicyContext context) {
        for (ILeadLoadPolicy policy : policys) {
            if (policy.load(sysUser, context)) {
                return;
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        Collections.sort(policys, new Comparator<ILeadLoadPolicy>() {

            @Override
            public int compare(ILeadLoadPolicy o1, ILeadLoadPolicy o2) {
                // TODO Auto-generated method stub
                return o1.getOrder() - o2.getOrder();
            }

        });
    }

}
