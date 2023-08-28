package com.wxsl.rosalind.spring.ioc.api;

import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("beanNameAwareDemo")
class BeanNameAwareDemoFrameWorkTest extends BaseTest {

    @Test
    @DisplayName("beanNameAware")
    void beanNameAwareDemo() {
        BeanNameAwareDemo beanNameAwareDemo = applicationContext.getBean("beanNameAwareDemo", BeanNameAwareDemo.class);
        Assertions.assertNotNull(beanNameAwareDemo);
    }
}
