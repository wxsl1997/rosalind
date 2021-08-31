package com.wxsl.rosalind.framework.web;

import com.wxsl.rosalind.framework.web.util.DateTimeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Nonnull;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(@Nonnull FormatterRegistry registry) {
        DateTimeFormatterRegistrar dateTimeRegistrar = new DateTimeFormatterRegistrar();
        dateTimeRegistrar.setDateTimeFormatter(DateTimeUtils.DATE_TIME_FORMATTER);
        dateTimeRegistrar.setDateFormatter(DateTimeUtils.DATE_FORMATTER);
        dateTimeRegistrar.setTimeFormatter(DateTimeUtils.TIME_FORMATTER);
        dateTimeRegistrar.registerFormatters(registry);
    }
}
