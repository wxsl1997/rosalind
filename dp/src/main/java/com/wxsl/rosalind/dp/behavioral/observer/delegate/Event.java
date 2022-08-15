package com.wxsl.rosalind.dp.behavioral.observer.delegate;

import java.lang.reflect.Method;

public class Event {

    private Object object;

    private String methodName;

    private Object[] params;

    public Event(Object object, String methodName, Object[] params) {
        this.object = object;
        this.methodName = methodName;
        this.params = params;
    }

    private Class[] paramTypes(Object[] params) {
        Class[] paramTypes = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            paramTypes[i] = params[i].getClass();
        }
        return paramTypes;
    }

    public void invoke() throws Exception {
        Method method = object.getClass().getMethod(methodName, paramTypes(params));
        method.invoke(object, params);
    }
}
