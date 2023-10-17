package com.wxsl.rosalind.designpattern.structural.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 */
public class CglibProxyHost implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        /*
         * 转发调用
         */
        return methodProxy.invokeSuper(object, args);
    }
}
