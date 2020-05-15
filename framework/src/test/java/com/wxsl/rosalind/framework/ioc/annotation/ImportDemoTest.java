package com.wxsl.rosalind.framework.ioc.annotation;

import com.wxsl.rosalind.framework.ioc.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@DisplayName("importDemo")
class ImportDemoTest {

    @Test
    @DisplayName("import")
    void importDemo() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportDemo.class);
        Assertions.assertNotNull(applicationContext.getBean("iPhone", Product.class));
    }
}
