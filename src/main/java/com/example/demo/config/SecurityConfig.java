package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security配置
 *
 * @author xiaobin.wang@marketin.cn
 * @create 2018-11-17 0:24
 * @since 2.16.6
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * HttpBasic认证
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .httpBasic()//开启httpbasic认证
//                .and()
//                .authorizeRequests()//Security表达式
//                .anyRequest().authenticated();//所有请求都需要认证
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/css/*.css").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")  // 认证页面
                .successForwardUrl("/home")
//                .loginProcessingUrl("/login")
                .permitAll();

    }
}