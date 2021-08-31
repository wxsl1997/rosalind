package com.wxsl.rosalind.security.config;

import com.wxsl.rosalind.security.config.local.LocalAuthenticationFilter;
import com.wxsl.rosalind.security.config.local.LocalAuthenticationProvider;
import com.wxsl.rosalind.security.config.local.RosalindAuthenticationFailureHandler;
import com.wxsl.rosalind.security.config.local.RosalindAuthenticationSuccessHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import static com.wxsl.rosalind.security.config.local.Constants.*;

@Slf4j
@EnableWebSecurity
@Configuration
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    SpringSessionBackedSessionRegistry<? extends Session> springSessionBackedSessionRegistry;

    LocalAuthenticationProvider localAuthenticationProvider;

    @Bean
    public LocalAuthenticationFilter localAuthenticationFilter() throws Exception {
        LocalAuthenticationFilter authenticationFilter = new LocalAuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(new RosalindAuthenticationSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(new RosalindAuthenticationFailureHandler());
        authenticationFilter.setAuthenticationManager(authenticationManager());
        // concurrent control
        authenticationFilter.setSessionAuthenticationStrategy(new ConcurrentSessionControlAuthenticationStrategy(springSessionBackedSessionRegistry));
        return authenticationFilter;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().mvcMatchers(ERROR_PAGE);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(localAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //任何请求需要身份验证
        http.authorizeRequests().anyRequest().authenticated();

        //csrf
        http.csrf().disable();

        //配置登录页面
        http.formLogin().loginPage(LOGIN_PAGE).permitAll();

        //配置会话管理
        http.sessionManagement().maximumSessions(MAXIMUM_SESSIONS).sessionRegistry(springSessionBackedSessionRegistry).maxSessionsPreventsLogin(true);

        http.addFilterBefore(localAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
