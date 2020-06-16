package com.wxsl.rosalind.framework.ioc.api;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.framework.ioc.annotation.DependOnDemo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DependOnDemo")
class DependOnDemoFrameWorkTest extends BaseTest {

    @Test
    @DisplayName("applicationContextAware")
    void dependOnDemo() {
        DependOnDemo dependOnDemo = applicationContext().getBean("dependOnDemo", DependOnDemo.class);
        Assertions.assertNotNull(dependOnDemo);
        Assertions.assertNotNull(dependOnDemo.getApplicationContext());
    }
}
