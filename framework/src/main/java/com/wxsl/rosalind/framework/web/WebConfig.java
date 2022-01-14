package com.wxsl.rosalind.framework.web;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.wxsl.rosalind.framework.web.annotation.LastSecondFormatterFactory;
import com.wxsl.rosalind.framework.web.util.DateUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(@Nonnull FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new LastSecondFormatterFactory());
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer builderCustomizer() {
        return builder -> {
            // Long
            builder.serializerByType(Long.class, ToStringSerializer.instance);

            // LocalDate
            builder.serializerByType(LocalDate.class, new LocalDateSerializer(DateUtils.DATE_FORMATTER));
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(DateUtils.DATE_FORMATTER));

            // LocalDateTime
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateUtils.DATE_TIME_FORMATTER));
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateUtils.DATE_TIME_FORMATTER));
        };
    }
}
