package com.wxsl.rosalind.spring.ioc.api;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.spring.ioc.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ApplicationEventPublisherDemo")
class ApplicationEventPublisherDemoTest extends BaseTest {

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
