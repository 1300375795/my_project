package com.ydg.myproject.security;

import com.ydg.myproject.config.CustomAuthenticationEntryPoint;
import com.ydg.myproject.config.RestAuthenticationAccessDeniedHandler;
import com.ydg.myproject.token.AuthenticationServer;
import com.ydg.myproject.token.TokenServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author ydg
 * @date 2018/7/20
 * @description
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${cloud.security.permitUrl}")
    private String permitUrl;
    @Value("${cloud.security.protected.url}")
    private String protectedUrl;
    @Autowired
    private TokenServer tokenServer;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll().antMatchers(HttpMethod.POST, permitUrl)
                .permitAll().anyRequest().authenticated()
                // .and().exceptionHandling().accessDeniedHandler(getAccessDeniedHandler())
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(getAccessDeniedHandler()).and()
                .addFilterBefore(new AuthenticationServer(protectedUrl, tokenServer, redisTemplate),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new RestAuthenticationAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }
}
