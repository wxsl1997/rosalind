package com.wxsl.rosalind.framework.ioc.annotation;

import com.wxsl.rosalind.framework.BaseFrameWorkTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("postConstructDemo")
class PostConstructDemoFrameWorkTest extends BaseFrameWorkTest {

    @Test
    @DisplayName("postConstruct")
    void postConstructDemo() {
        Assertions.assertNotNull(applicationContext.getBean("postConstructDemo", PostConstructDemo.class));
        applicationContext.close();
    }
}
