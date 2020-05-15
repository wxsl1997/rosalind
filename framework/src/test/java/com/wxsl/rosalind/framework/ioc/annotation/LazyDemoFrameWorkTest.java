package com.wxsl.rosalind.framework.ioc.annotation;

import com.wxsl.rosalind.framework.BaseFrameWorkTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("lazyDemo")
class LazyDemoFrameWorkTest extends BaseFrameWorkTest {

    @Test
    @DisplayName("lazy")
    void lazyDemo() {
        Assertions.assertNotNull(applicationContext.getBean("lazyDemo", LazyDemo.class));
    }
}
