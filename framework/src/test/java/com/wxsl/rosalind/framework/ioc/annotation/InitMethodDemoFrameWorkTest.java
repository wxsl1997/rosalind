package com.wxsl.rosalind.framework.ioc.annotation;

import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("InitMethodDemo")
class InitMethodDemoFrameWorkTest extends BaseTest {

    @Test
    @DisplayName("init")
    void initMethodDemo() {
        Assertions.assertNotNull(applicationContext().getBean("initMethodDemo", InitMethodDemo.class));
    }
}
