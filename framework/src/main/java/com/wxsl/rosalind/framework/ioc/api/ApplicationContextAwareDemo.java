package com.wxsl.rosalind.framework.ioc.api;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextAwareDemo implements ApplicationContextAware {

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        if (applicationContext instanceof GenericApplicationContext) {
            ((GenericApplicationContext) applicationContext).registerShutdownHook();
        }
    }
}
