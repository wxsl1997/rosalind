package com.wxsl.rosalind.framework.ioc.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Component
public class PropertyEditorDemo {

    @Value("2020-01-01 12:12:12")
    private Date date;

    @Value("2020-02-02 12:12:12")
    private LocalDateTime localDateTime;
}
