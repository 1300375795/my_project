package com.ydg.myproject.annotation.configuration;

import com.ydg.myproject.annotation.bean.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yedeguo
 * @Company qcsz
 * @date 2018-12-15
 * @description
 */
@Configuration
public class TestConfiguration {
    @Bean
    public TestBean testBean(){
        return  new TestBean();
    }
}
