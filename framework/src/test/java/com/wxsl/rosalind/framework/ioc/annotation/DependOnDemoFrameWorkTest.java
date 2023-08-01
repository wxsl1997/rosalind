package com.wxsl.rosalind.framework.ioc.annotation;

import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("dependOnDemo")
class DependOnDemoFrameWorkTest extends BaseTest {

    @Test
    @DisplayName("dependOn")
    void dependOnDemo() {
        DependOnDemo dependOnDemo = applicationContext.getBean("dependOnDemo", DependOnDemo.class);
        Assertions.assertNotNull(dependOnDemo);
        Assertions.assertNotNull(dependOnDemo.iPhone());
    }
}
