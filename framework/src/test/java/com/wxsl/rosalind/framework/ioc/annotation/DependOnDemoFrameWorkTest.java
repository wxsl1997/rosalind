package com.wxsl.rosalind.framework.ioc.annotation;

import com.wxsl.rosalind.framework.BaseFrameWorkTest;
import org.junit.jupiter.api.*;

@DisplayName("dependOnDemo")
class DependOnDemoFrameWorkTest extends BaseFrameWorkTest {

    @Test
    @DisplayName("dependOn")
    void dependOnDemo() {
        DependOnDemo dependOnDemo = applicationContext.getBean("dependOnDemo", DependOnDemo.class);
        Assertions.assertNotNull(dependOnDemo);
        Assertions.assertNotNull(dependOnDemo.iPhone());
    }
}
