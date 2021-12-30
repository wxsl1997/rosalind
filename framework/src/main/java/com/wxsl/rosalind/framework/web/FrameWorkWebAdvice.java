package com.wxsl.rosalind.framework.web;

import com.wxsl.rosalind.framework.ioc.api.LocalDateTimePropertyEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.time.LocalDateTime;

@ControllerAdvice
public class FrameWorkWebAdvice {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(LocalDateTime.class, new LocalDateTimePropertyEditor());
    }
}
