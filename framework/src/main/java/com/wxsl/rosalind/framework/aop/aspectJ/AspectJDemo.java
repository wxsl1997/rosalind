package com.wxsl.rosalind.framework.aop.aspectJ;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class AspectJDemo {

    @Pointcut("execution(* com.wxsl.rosalind.framework.aop.service.UserService.loginIn(..))")
    public void loginInPointCut() {/*定义 切点*/}

    @Pointcut("execution(* com.wxsl.rosalind.framework.aop.service.UserService.loginOut(..))")
    public void loginOutPointCut() {/*定义 切点*/}

    @Before("loginInPointCut() || loginOutPointCut()")
    public void before(JoinPoint joinPoint) {
        log.info("args:{}", joinPoint.getArgs());
        log.info("aspectj before");
    }

    @After("loginInPointCut() || loginOutPointCut()")
    public void after() {
        log.info("aspectj after");
    }

    @AfterReturning("loginInPointCut() || loginOutPointCut()")
    public void afterReturning() {
        log.info("aspectj after return");
    }

    @AfterThrowing("loginInPointCut() || loginOutPointCut()")
    public void afterThrowing() {
        log.info("aspectj after throw");
    }

    @Around("loginInPointCut() || loginOutPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("aspectj around before");
        Object result = proceedingJoinPoint.proceed();
        log.info("result:{}", result);
        log.info("aspectj around after");
        return result;
    }
}
