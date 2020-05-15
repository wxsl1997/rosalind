package com.wxsl.rosalind.framework.ioc.annotation;

import com.wxsl.rosalind.framework.BaseFrameWorkTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("scopeDemo")
class ScopeDemoFrameWorkTest extends BaseFrameWorkTest {

    @Test
    @DisplayName("scope")
    void scopeDemo() {
        LookupDemo lookupDemo = applicationContext.getBean("lookupDemo", LookupDemo.class);
        Assertions.assertNotSame(lookupDemo.getScopeDemo(), lookupDemo.getScopeDemo());
        applicationContext.close();
    }
}
