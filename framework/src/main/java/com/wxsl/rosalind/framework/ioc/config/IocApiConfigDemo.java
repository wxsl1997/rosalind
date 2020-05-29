package com.wxsl.rosalind.framework.ioc.config;

import com.google.common.collect.ImmutableMap;
import com.wxsl.rosalind.framework.ioc.api.FactoryBeanDemo;
import com.wxsl.rosalind.framework.ioc.api.LocalDateTimePropertyEditor;
import com.wxsl.rosalind.framework.ioc.api.PropertyEditorRegistrarDemo;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.beans.PropertyEditor;
import java.time.LocalDateTime;
import java.util.Map;

@Configuration
public class IocApiConfigDemo {

    private static final Map<Class<?>, Class<? extends PropertyEditor>> propertyEditorMap = ImmutableMap.<Class<?>, Class<? extends PropertyEditor>>builder()
            .put(LocalDateTime.class, LocalDateTimePropertyEditor.class)
            .build();


    @Bean
    public FactoryBeanDemo factoryBeanDemo() {
        return new FactoryBeanDemo("MD5");
    }


    @Bean
    public static PropertyEditorRegistrarDemo propertyEditorRegistrarDemo() {
        return new PropertyEditorRegistrarDemo();
    }

    @Bean
    public static CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        customEditorConfigurer.setCustomEditors(propertyEditorMap);
        customEditorConfigurer.setPropertyEditorRegistrars(new PropertyEditorRegistrar[]{propertyEditorRegistrarDemo()});
        return customEditorConfigurer;
    }

    @Profile("dev")
    @Bean(name = "profileDemo")
    @ConfigurationProperties("dev.profile")
    ProfileDemo devProfileDemo() {
        return new ProfileDemo();
    }

    @Profile("test")
    @Bean(name = "profileDemo")
    @ConfigurationProperties("test.profile")
    ProfileDemo testProfileDemo() {
        return new ProfileDemo();
    }
}
