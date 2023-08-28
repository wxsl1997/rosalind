package com.wxsl.rosalind.spring.ioc.api;


import com.wxsl.rosalind.spring.util.TimeUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.util.Optional;

public class LocalDateTimePropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {

        LocalDateTime dateTime = Optional.ofNullable(text)
                .map(StringUtils::trimToNull)
                .map(s -> LocalDateTime.parse(s, TimeUtils.DATE_TIME_FORMATTER))
                .orElse(null);

        setValue(dateTime);
    }
}
