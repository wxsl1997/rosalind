package com.wxsl.rosalind.framework.aop.config;

import com.wxsl.rosalind.framework.BaseFrameWorkTest;
import com.wxsl.rosalind.framework.aop.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AopApiConfigDemo")
class AopApiConfigDemoTest extends BaseFrameWorkTest {

    @Test
    @DisplayName("proxyFactoryBean")
    void proxyFactoryBean() {
        User user = applicationContext.getBean("proxyFactoryBean", User.class);
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getAccount());
    }
}
