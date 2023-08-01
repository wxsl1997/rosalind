package com.wxsl.rosalind.framework.ioc.annotation;

import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("postConstructDemo")
class PostConstructDemoFrameWorkTest extends BaseTest {

    @Test
    @DisplayName("postConstruct")
    void postConstructDemo() {
        Assertions.assertNotNull(applicationContext.getBean("postConstructDemo", PostConstructDemo.class));
    }
}
