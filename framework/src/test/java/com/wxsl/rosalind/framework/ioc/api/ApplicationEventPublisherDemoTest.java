package com.wxsl.rosalind.framework.ioc.api;

import com.wxsl.rosalind.framework.BaseFrameWorkTest;
import com.wxsl.rosalind.framework.ioc.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ApplicationEventPublisherDemo")
class ApplicationEventPublisherDemoTest extends BaseFrameWorkTest {

    @Test
    @DisplayName("applicationEventPublisher")
    void publishEvent() {
        ApplicationEventPublisherDemo publisher = applicationContext.getBean(ApplicationEventPublisherDemo.class);

        Product macBook = applicationContext.getBean("macBook", Product.class);

        publisher.publishEvent(macBook);
    }

    @Test
    @DisplayName("transactionalEventListener")
    void publishTxEvent() {
        ApplicationEventPublisherDemo publisher = applicationContext.getBean(ApplicationEventPublisherDemo.class);

        Product macBook = applicationContext.getBean("macBook", Product.class);

        publisher.publishTxEvent(macBook);
    }
}
