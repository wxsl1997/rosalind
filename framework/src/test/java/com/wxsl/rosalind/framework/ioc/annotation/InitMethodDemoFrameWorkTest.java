package com.wxsl.rosalind.framework.ioc.annotation;

import com.wxsl.rosalind.framework.BaseFrameWorkTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("initMethodDemo")
class InitMethodDemoFrameWorkTest extends BaseFrameWorkTest {

    @Test
    @DisplayName("init")
    void initMethodDemo() {
        Assertions.assertNotNull(applicationContext.getBean("initMethodDemo", InitMethodDemo.class));
    }
}
