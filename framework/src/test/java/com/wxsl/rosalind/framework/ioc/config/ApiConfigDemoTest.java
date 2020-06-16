package com.wxsl.rosalind.framework.ioc.config;

import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.CustomEditorConfigurer;

@DisplayName("customEditorConfigurerDemo")
class ApiConfigDemoTest extends BaseTest {


    @Test
    @DisplayName("customEditorConfigurer")
    void customEditorConfigurer() {
        CustomEditorConfigurer customEditorConfigurerDemo = applicationContext().getBean("customEditorConfigurer", CustomEditorConfigurer.class);
        Assertions.assertNotNull(customEditorConfigurerDemo);
    }


    @Test
    @DisplayName("propertyEditorRegistrars")
    void propertyEditorRegistrars() {
        PropertyEditorDemo propertyEditorDemo = applicationContext().getBean("propertyEditorDemo", PropertyEditorDemo.class);
        Assertions.assertNotNull(propertyEditorDemo.getDate());
    }

    @Test
    @DisplayName("customEditors")
    void customEditors() {
        PropertyEditorDemo propertyEditorDemo = applicationContext().getBean("propertyEditorDemo", PropertyEditorDemo.class);
        Assertions.assertNotNull(propertyEditorDemo.getLocalDateTime());
    }
}
