package com.wxsl.rosalind.framework.web;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class FrameWorkControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        //@see org.springframework.beans.TypeConverterDelegate.convertIfNecessary
    }
}
