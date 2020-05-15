package com.wxsl.rosalind.framework.ioc.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component
public class LazyDemo {

    public LazyDemo() {
        log.info("lazyDemo");
    }
}
