package com.wxsl.rosalind.spring.aop.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

@Slf4j
public class ThrowsAdviceDemo implements ThrowsAdvice {

    public void afterThrowing(Exception e) throws Exception {
        log.info("exception", e);
        throw new Exception("exception");
    }

    public void afterThrowing(IllegalArgumentException e) throws Exception {
        log.info("illegalArgumentException", e);
        throw new Exception("illegalArgumentException");
    }

    public void afterThrowing(Method method, Object[] args, Object target, RuntimeException e) throws Exception {
        log.info("runtimeException", e);
        throw new Exception("runtimeException");
    }

    public void afterThrowing(Method method, Object[] args, Object target, UnsupportedOperationException e) throws Exception {
        log.info("unsupportedOperationException)", e);
        throw new Exception("unsupportedOperationException");
    }
}
