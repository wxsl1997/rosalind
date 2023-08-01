package com.wxsl.rosalind.framework.ioc.api;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.framework.ioc.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ApplicationEventPublisherAwareDemo")
class ApplicationEventPublisherAwareDemoTest extends BaseTest {

    @Test
    @DisplayName("applicationEventPublisherAware")
    void publishEvent() {
        ApplicationEventPublisherAwareDemo publisher = applicationContext.getBean(ApplicationEventPublisherAwareDemo.class);

        Product macBook = applicationContext.getBean("macBook", Product.class);

        publisher.publishEvent(macBook);
    }
}
