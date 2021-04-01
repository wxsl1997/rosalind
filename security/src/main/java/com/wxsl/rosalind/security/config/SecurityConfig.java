package com.wxsl.rosalind.security.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

@Slf4j
@EnableWebSecurity
@Configuration
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PAGE = "/login.html";

    private static final String LOGIN_URL = "/login";

    private static final String INDEX_PAGE = "/index.html";

    private static final String ERROR_PAGE = "/error.html";

    private static final int MAXIMUM_SESSIONS = 2;

    SpringSessionBackedSessionRegistry<? extends Session> springSessionBackedSessionRegistry;

    LocalAuthenticationProvider localAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) {
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(localAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //任何请求需要身份验证
        http.authorizeRequests().anyRequest().authenticated();

        //关闭 csrf
        http.csrf().disable();

        //配置登录页面
        http.formLogin()
                //登录页面
                .loginPage(LOGIN_PAGE)
                .successHandler((request, response, authentication) -> log.error("login success"))
                .failureHandler((request, response, exception) -> log.error("login fail", exception))
                //登录 url
                .loginProcessingUrl(LOGIN_URL)
                //登录成功跳转页面
                .defaultSuccessUrl(INDEX_PAGE)
                //登录失败跳转页面
                .failureUrl(ERROR_PAGE)
                .permitAll();

        //配置会话管理
        http.sessionManagement().maximumSessions(MAXIMUM_SESSIONS).sessionRegistry(springSessionBackedSessionRegistry);
    }
}
