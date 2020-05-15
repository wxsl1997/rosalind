package com.wxsl.rosalind.framework.ioc.api;

import com.wxsl.rosalind.framework.BaseFrameWorkTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("beanNameAwareDemo")
class BeanNameAwareDemoFrameWorkTest extends BaseFrameWorkTest {

    @Test
    @DisplayName("beanNameAware")
    void beanNameAwareDemo() {
        BeanNameAwareDemo beanNameAwareDemo = applicationContext.getBean("beanNameAwareDemo", BeanNameAwareDemo.class);
        Assertions.assertNotNull(beanNameAwareDemo);
    }
}
