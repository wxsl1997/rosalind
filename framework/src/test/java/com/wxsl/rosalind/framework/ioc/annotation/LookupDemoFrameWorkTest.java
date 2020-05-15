package com.wxsl.rosalind.framework.ioc.annotation;

import com.wxsl.rosalind.framework.BaseFrameWorkTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("lookupDemo")
class LookupDemoFrameWorkTest extends BaseFrameWorkTest {

    @Test
    @DisplayName("lookup")
    void lookupDemo() {
        Assertions.assertNotNull(applicationContext.getBean("lookupDemo", LookupDemo.class));
    }
}
