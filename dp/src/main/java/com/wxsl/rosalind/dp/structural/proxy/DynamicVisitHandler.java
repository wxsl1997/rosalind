package com.wxsl.rosalind.dp.structural.proxy;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理
 */
@AllArgsConstructor
public class DynamicVisitHandler implements InvocationHandler {

    /**
     * 真实主题对象引用
     */
    private VisitHost visitHost;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /*
         * 转发调用
         */
        return method.invoke(visitHost, args);
    }
}
