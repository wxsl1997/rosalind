package com.wxsl.rosalind.framework.ioc.api;

import com.wxsl.rosalind.framework.BaseFrameWorkTest;
import com.wxsl.rosalind.framework.ioc.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ApplicationEventPublisherAwareDemo")
class ApplicationEventPublisherAwareDemoTest extends BaseFrameWorkTest {

    @Test
    @DisplayName("applicationEventPublisherAware")
    void publishEvent() {
        ApplicationEventPublisherAwareDemo publisher = applicationContext.getBean(ApplicationEventPublisherAwareDemo.class);

        Product macBook = applicationContext.getBean("macBook", Product.class);

        publisher.publishEvent(macBook);
    }
}
