package com.wxsl.rosalind.framework.aop.api;

import com.wxsl.rosalind.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import java.util.concurrent.Callable;

import static com.wxsl.rosalind.framework.aop.api.ProxyFactoryDemo.proxyFactory;

@Slf4j
@DisplayName("ThrowsAdviceDemo")
class ThrowsAdviceDemoTest extends BaseTest {

    @Test
    @DisplayName("testException")
    void testException() {
        Callable<Object> target = () -> {
            throw new Exception("exception test");
        };
        try {
            testThrowsAdvice(target);
        } catch (Exception e) {
            log.info("call failed", e);
        }
    }

    @Test
    @DisplayName("testNullPointException")
    void testNullPointException() {
        Callable<Object> target = () -> {
            throw new NullPointerException("method args target runtimeException test");
        };
        try {
            testThrowsAdvice(target);
        } catch (Exception e) {
            log.info("call failed", e);
        }
    }

    @Test
    @DisplayName("testIllegalArgumentException")
    void testIllegalArgumentException() {
        Callable<Object> target = () -> {
            throw new IllegalArgumentException("illegalArgumentException test");
        };
        try {
            testThrowsAdvice(target);
        } catch (Exception e) {
            log.info("call failed", e);
        }
    }

    @Test
    @DisplayName("testUnsupportedOperationException")
    void testUnsupportedOperationException() {
        Callable<Object> target = () -> {
            throw new UnsupportedOperationException("unsupportedOperationException test");
        };
        try {
            testThrowsAdvice(target);
        } catch (Exception e) {
            log.info("call failed", e);
        }
    }

    private void testThrowsAdvice(Callable<Object> target) throws Exception {
        ProxyFactory proxyFactory = proxyFactory(target, new ThrowsAdviceDemo());
        Callable<?> proxy = (Callable<?>) proxyFactory.getProxy();
        proxy.call();
    }
}
