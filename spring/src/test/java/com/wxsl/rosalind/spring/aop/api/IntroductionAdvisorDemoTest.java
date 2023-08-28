package com.wxsl.rosalind.spring.aop.api;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.spring.aop.model.User;
import com.wxsl.rosalind.spring.aop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

@Slf4j
@DisplayName("IntroductionAdvisorDemo")
class IntroductionAdvisorDemoTest extends BaseTest {

    @Test
    @DisplayName("defaultIntroductionAdvisor")
    void defaultIntroductionAdvisor() {

        IntroductionAdvisor introductionAdvisor = new DefaultIntroductionAdvisor(methodBeforeAdvice());

        testIntroductionAdvisor(introductionAdvisor);
    }

    private void testIntroductionAdvisor(IntroductionAdvisor introductionAdvisor) {

        //获取 被代理对象
        UserService userService = applicationContext.getBean(UserService.class);

        //获取 ProxyFactory
        ProxyFactory proxyFactory = new ProxyFactory();

        //设置 顾问
        proxyFactory.addAdvisor(introductionAdvisor);

        //设置 目标对象
        proxyFactory.setTarget(userService);

        //设置 优化
        proxyFactory.setOptimize(true);

        //获取 代理对象
        UserService proxy = (UserService) proxyFactory.getProxy();

        //获取 用户实例
        User user = applicationContext.getBean("hermia", User.class);

        //调用代理方法
        user = proxy.loginOut(user);

        Assertions.assertNotNull(user);
    }

    private Advice methodBeforeAdvice() {
        return (MethodBeforeAdvice) (method, args, target) -> log.info("methodBeforeAdvice before");
    }
}
