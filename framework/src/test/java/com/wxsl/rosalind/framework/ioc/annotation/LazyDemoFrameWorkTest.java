package com.wxsl.rosalind.framework.ioc.annotation;

import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("lazyDemo")
class LazyDemoFrameWorkTest extends BaseTest {

    @Test
    @DisplayName("lazy")
    void lazyDemo() {
        Assertions.assertNotNull(applicationContext().getBean("lazyDemo", LazyDemo.class));
    }
}
