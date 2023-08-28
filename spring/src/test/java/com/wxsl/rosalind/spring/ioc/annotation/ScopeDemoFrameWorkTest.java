package com.wxsl.rosalind.spring.ioc.annotation;

import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("scopeDemo")
class ScopeDemoFrameWorkTest extends BaseTest {

    @Test
    @DisplayName("scope")
    void scopeDemo() {
        LookupDemo lookupDemo = applicationContext.getBean("lookupDemo", LookupDemo.class);
        Assertions.assertNotSame(lookupDemo.getScopeDemo(), lookupDemo.getScopeDemo());
    }
}
