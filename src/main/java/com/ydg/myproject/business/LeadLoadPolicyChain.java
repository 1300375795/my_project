package com.ydg.myproject.business;

import com.ydg.myproject.entity.SysUser;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
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
        log.info("===============**没有排序前**================");
        for (ILeadLoadPolicy policy : policys) {
            log.info(policy.getClass().getName());
            log.info("改策略的order是：{}", policy.getOrder());

        }

        Collections.sort(policys, new Comparator<ILeadLoadPolicy>() {
            @Override
            public int compare(ILeadLoadPolicy o1, ILeadLoadPolicy o2) {
                // TODO Auto-generated method stub
                return o1.getOrder() - o2.getOrder();
            }
        });

        log.info("\n");
        log.info("===============**排序后**================");
        for (ILeadLoadPolicy policy : policys) {
            log.info(policy.getClass().getName());
            int order = policy.getOrder();
            log.info("改策略的order是：{}", order);
        }
        log.info("===============**结束方法**================");
    }

}
