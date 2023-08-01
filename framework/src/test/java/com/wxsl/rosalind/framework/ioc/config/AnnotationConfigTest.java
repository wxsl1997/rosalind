package com.wxsl.rosalind.framework.ioc.config;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.framework.ioc.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("annotationConfigDemo")
class AnnotationConfigTest extends BaseTest {

    @Test
    @DisplayName("bean")
    void iPhone() {
        Product iPhone = applicationContext.getBean("iPhone", Product.class);
        Assertions.assertNotNull(iPhone);
    }

    @Test
    @DisplayName("configuration")
    void airPods() {
        Product airPods = applicationContext.getBean("airPods", Product.class);
        Assertions.assertNotNull(airPods);
    }

    @Test
    @DisplayName("componentScan")
    void macBook() {
        Product macBook = applicationContext.getBean("macBook", Product.class);
        Assertions.assertNotNull(macBook);
    }
}
