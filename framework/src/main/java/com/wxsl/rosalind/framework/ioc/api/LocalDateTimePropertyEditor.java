package com.wxsl.rosalind.framework.ioc.api;


import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimePropertyEditor extends PropertyEditorSupport {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void setAsText(String text) {
        if (!StringUtils.hasText(text)) {
            setValue(null);
            return;
        }
        setValue(LocalDateTime.parse(text, DATE_TIME_FORMATTER));
    }
}
