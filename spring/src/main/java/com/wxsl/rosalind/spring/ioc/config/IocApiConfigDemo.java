package com.wxsl.rosalind.spring.ioc.config;

import com.google.common.collect.ImmutableMap;
import com.wxsl.rosalind.spring.ioc.api.FactoryBeanDemo;
import com.wxsl.rosalind.spring.ioc.api.LocalDateTimePropertyEditor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Configuration
public class IocApiConfigDemo {

    private static final Map<Class<?>, Class<? extends PropertyEditor>> PROPERTY_EDITOR_MAP = ImmutableMap.<Class<?>, Class<? extends PropertyEditor>>builder()
            .put(LocalDateTime.class, LocalDateTimePropertyEditor.class)
            .build();

    @Bean
    public FactoryBeanDemo factoryBeanDemo() {
        return new FactoryBeanDemo("MD5");
    }

    @Bean
    public static PropertyEditorRegistrar datePropertyEditorRegistrar() {
        return registry -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            CustomDateEditor customDateEditor = new CustomDateEditor(simpleDateFormat, true);
            registry.registerCustomEditor(Date.class, customDateEditor);
        };
    }

    @Bean
    public static CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        customEditorConfigurer.setCustomEditors(PROPERTY_EDITOR_MAP);
        customEditorConfigurer.setPropertyEditorRegistrars(ArrayUtils.toArray(datePropertyEditorRegistrar()));
        return customEditorConfigurer;
    }

    @Bean
    @ConfigurationProperties("profile.demo")
    ProfileDemo profileDemo() {
        return new ProfileDemo();
    }
}
