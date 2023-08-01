package com.wxsl.rosalind.framework.aop.api;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.framework.aop.annotation.ApiMethod;
import com.wxsl.rosalind.framework.aop.model.User;
import com.wxsl.rosalind.framework.aop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.*;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.Objects;

import static com.wxsl.rosalind.framework.aop.api.ProxyFactoryDemo.proxyFactory;

@Slf4j
@DisplayName("PointCutDemo")
class PointCutDemoTest extends BaseTest {

    @Test
    @DisplayName("staticMethodMatcherPointcut")
    void staticMethodMatcherPointcut() {

        Pointcut pointcut = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass) {
                return method.getName().equals("loginOut");
            }
        };

        testPointCut(pointcut);
    }

    @Test
    @DisplayName("dynamicMethodMatcherPointcut")
    void dynamicMethodMatcherPointcut() {

        Pointcut pointcut = new DynamicMethodMatcherPointcut() {
            @Override
            public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass, @Nullable Object... args) {
                User user = (User) Objects.requireNonNull(args)[0];
                return "hermia".equals(user.getAccount());
            }
        };

        testPointCut(pointcut);
    }

    @Test
    @DisplayName("nameMatchMethodPointcut")
    void nameMatchMethodPointcut() {

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("loginOut");

        testPointCut(pointcut);
    }

    @Test
    @DisplayName("jdkRegexpMethodPointcut")
    void jdkRegexpMethodPointcut() {

        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*loginOut");

        testPointCut(pointcut);
    }

    @Test
    @DisplayName("aspectJExpressionPointcut")
    void aspectJExpressionPointcut() {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.wxsl.rosalind.framework.aop.service.UserService.*(..))");

        testPointCut(pointcut);
    }

    @Test
    @DisplayName("annotationMatchingPointcut")
    void annotationMatchingPointcut() {

        AnnotationMatchingPointcut pointcut = AnnotationMatchingPointcut.forMethodAnnotation(ApiMethod.class);

        testPointCut(pointcut);
    }

    @Test
    @DisplayName("controlFlowPointcut")
    void controlFlowPointcut() {

        ControlFlowPointcut pointcut = new ControlFlowPointcut(this.getClass(), "testPointCut");

        testPointCut(pointcut);
    }

    @Test
    @DisplayName("composablePointcut")
    void composablePointcut() {

        ComposablePointcut pointcut = new ComposablePointcut(ClassFilter.TRUE, new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass) {
                return method.getName().equals("loginOut");
            }
        });

        pointcut.union((Pointcut) new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass) {
                return method.getName().equals("loginIn");
            }
        });

        testPointCut(pointcut);
    }

    private void testPointCut(Pointcut pointcut) {
        //获取 被代理对象
        UserService userService = applicationContext.getBean(UserService.class);

        //获取 ProxyFactory, 设置前置增强
        ProxyFactory proxyFactory = proxyFactory(userService, methodInterceptor(), pointcut);

        //获取 代理对象
        UserService proxy = (UserService) proxyFactory.getProxy();

        //获取 用户实例
        User user = applicationContext.getBean("hermia", User.class);

        //调用代理方法
        user = proxy.loginOut(user);

        Assertions.assertNotNull(user);
    }

    private Advice methodInterceptor() {
        return (MethodInterceptor) invocation -> {
            log.info("methodInterceptor before");
            User user = (User) invocation.proceed();
            log.info("methodInterceptor after");
            return user;
        };
    }
}
