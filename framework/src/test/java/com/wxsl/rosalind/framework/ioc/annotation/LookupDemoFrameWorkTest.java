package com.wxsl.rosalind.framework.ioc.annotation;

import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("lookupDemo")
class LookupDemoFrameWorkTest extends BaseTest {

    @Test
    @DisplayName("lookup")
    void lookupDemo() {
        Assertions.assertNotNull(applicationContext.getBean("lookupDemo", LookupDemo.class));
    }
}
