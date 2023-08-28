package com.wxsl.rosalind.spring.ioc.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class BeanNameAwareDemo implements BeanNameAware {

    @Override
    public void setBeanName(@NonNull String name) {
        log.debug(name);
    }
}
