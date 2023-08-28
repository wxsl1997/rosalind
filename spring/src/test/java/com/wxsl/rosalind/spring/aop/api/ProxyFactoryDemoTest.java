package com.wxsl.rosalind.spring.aop.api;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.spring.aop.model.User;
import com.wxsl.rosalind.spring.aop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import static com.wxsl.rosalind.spring.aop.api.ProxyFactoryDemo.proxyFactory;

@Slf4j
@DisplayName("ProxyFactoryDemo")
class ProxyFactoryDemoTest extends BaseTest {

    @Test
    @DisplayName("methodBeforeAdvice")
    void methodBeforeAdvice() {
        Advice methodBeforeAdvice = (MethodBeforeAdvice) (method, args, target) -> log.info("methodBeforeAdvice");
        testAdvice(methodBeforeAdvice);
    }

    @Test
    @DisplayName("methodBeforeAdvice Exception")
    void methodBeforeAdviceException() {
        Advice methodBeforeAdvice = (MethodBeforeAdvice) (method, args, target) -> {
            throw new Exception("methodBeforeAdvice Exception");
        };

        try {
            testAdvice(methodBeforeAdvice);
        } catch (Exception e) {
            log.info("login failed", e);
        }
    }

    @Test
    @DisplayName("afterReturningAdvice")
    void afterReturningAdvice() {
        Advice afterRunningAdvice = (AfterReturningAdvice) (returnValue, method, args, target) -> log.info("afterReturningAdvice");
        testAdvice(afterRunningAdvice);
    }

    @Test
    @DisplayName("afterReturningAdvice Exception")
    void afterReturningAdviceException() {
        Advice afterRunningAdvice = (AfterReturningAdvice) (returnValue, method, args, target) -> {
            throw new Exception("afterReturningAdvice Exception");
        };

        try {
            testAdvice(afterRunningAdvice);
        } catch (Exception e) {
            log.info("login failed", e);
        }
    }

    @Test
    @DisplayName("methodInterceptor")
    void methodInterceptor() {
        Advice methodInterceptor = (MethodInterceptor) invocation -> {
            log.info("methodInterceptor before");
            User user = (User) invocation.proceed();
            log.info("methodInterceptor after");
            return user;
        };
        testAdvice(methodInterceptor);
    }

    private void testAdvice(Advice advice) {

        //获取 被代理对象
        UserService userService = applicationContext.getBean(UserService.class);

        //获取 ProxyFactory, 设置前置增强
        ProxyFactory proxyFactory = proxyFactory(userService, advice);

        //获取 代理对象
        UserService proxy = (UserService) proxyFactory.getProxy();

        //获取 用户实例
        User user = applicationContext.getBean("hermia", User.class);

        //调用代理方法
        user = proxy.loginIn(user);

        Assertions.assertNotNull(user);
    }
}
