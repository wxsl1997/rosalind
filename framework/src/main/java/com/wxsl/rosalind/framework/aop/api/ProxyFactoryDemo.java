package com.wxsl.rosalind.framework.aop.api;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ProxyFactoryDemo {

    public static ProxyFactory proxyFactory(Object target, Advice advice) {

        ProxyFactory proxyFactory = new ProxyFactory();
        //添加通知
        proxyFactory.addAdvice(advice);
        //设置目标对象
        proxyFactory.setTarget(target);
        return proxyFactory;
    }

    public static ProxyFactory proxyFactory(Object target, Advice advice, Pointcut pointcut) {

        ProxyFactory proxyFactory = new ProxyFactory();
        //添加切面
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(pointcut, advice));
        //设置目标对象
        proxyFactory.setTarget(target);
        return proxyFactory;
    }
}
