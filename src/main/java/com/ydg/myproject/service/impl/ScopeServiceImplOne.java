package com.ydg.myproject.service.impl;

import com.ydg.myproject.component.TestScope;
import com.ydg.myproject.service.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ydg
 * @Company qcsz
 * @date 2018-12-21
 * @description
 */
@Service
public class ScopeServiceImplOne implements ScopeService {
    @Autowired
    private TestScope testScope;

    /**
     * 打印自身
     */
    @Override
    public void logThis() {
        testScope.logThis();
    }
}
