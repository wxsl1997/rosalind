package com.wxsl.rosalind.spring.aop.config;

import com.wxsl.rosalind.spring.aop.model.User;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Slf4j
@Configuration
public class AopApiConfigDemo {

    @Bean
    public ProxyFactoryBean proxyFactoryBean() {

        //获取 通知
        Advice advice = methodInterceptor();

        //获取 切点
        Pointcut pointcut = aspectJExpressionPointcut();

        //创建 proxyFactoryBean
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();

        //设置 切面
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, advice));

        //设置 目标对象
        proxyFactoryBean.setTarget(helena());

        return proxyFactoryBean;
    }

    private AspectJExpressionPointcut aspectJExpressionPointcut() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //设置 切点表达式
        pointcut.setExpression("execution(* com.wxsl.rosalind.spring.aop.model.User.get*(..))");
        return pointcut;
    }

    private MethodInterceptor methodInterceptor() {
        return invocation -> {
            log.info("methodInterceptor before");
            Object result = invocation.proceed();
            log.info("{}", result);
            log.info("methodInterceptor after");
            return result;
        };
    }

    private User helena() {
        LocalDateTime time = LocalDateTime.now();
        return User.builder()
                .id(20001L)
                .name("海丽娜")
                .account("helena")
                .password("helena")
                .created(time)
                .modified(time)
                .build();
    }
}
