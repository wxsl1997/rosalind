package com.wxsl.rosalind.framework.ioc.api;

import com.wxsl.rosalind.framework.BaseFrameWorkTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@DisplayName("factoryBeanDemo")
class FactoryBeanDemoTest extends BaseFrameWorkTest {

    @Test
    @DisplayName("factoryBean")
    void factoryBeanDemo() {
        MessageDigest messageDigest = applicationContext.getBean("factoryBeanDemo", MessageDigest.class);
        Assertions.assertNotNull(messageDigest);
    }

    @Test
    @DisplayName("&factoryBean")
    void getObject() throws NoSuchAlgorithmException {
        FactoryBeanDemo factoryBeanDemo = applicationContext.getBean("&factoryBeanDemo", FactoryBeanDemo.class);
        Assertions.assertNotNull(factoryBeanDemo);
        Assertions.assertNotNull(factoryBeanDemo.getObject());
    }
}
