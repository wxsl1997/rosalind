package com.wxsl.rosalind.dp.structural.proxy;

import net.sf.cglib.proxy.Enhancer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@DisplayName("代理模式")
class StaticProxyHostTest {

    @Test
    @DisplayName("代理-静态")
    void visit() {
        VisitHost visitHost = new StaticProxyHost();
        visitHost.visit();
    }


    @Test
    @DisplayName("代理-动态")
    void invoke() {

        //真实代理对象
        VisitHost visitHost = new DellHost();

        //代理处理者
        InvocationHandler invocationHandler = new DynamicVisitHandler(visitHost);

        //动态创建代理对象
        VisitHost dynamicProxy = (VisitHost) Proxy.newProxyInstance(VisitHost.class.getClassLoader(), new Class[]{VisitHost.class}, invocationHandler);

        //调用代理对象
        dynamicProxy.visit();
    }

    @Test
    @DisplayName("代理-cglib")
    void intercept() {

        //创建增强器
        Enhancer enhancer = new Enhancer();

        //设置代理对象父类类型
        enhancer.setSuperclass(DellHost.class);

        //设置回调函数
        enhancer.setCallback(new CglibProxyHost());

        //创建代理对象
        DellHost cglibProxy = (DellHost) enhancer.create();

        //调用代理对象
        cglibProxy.visit();
    }
}
