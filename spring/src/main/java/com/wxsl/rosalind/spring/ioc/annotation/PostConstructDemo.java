package com.wxsl.rosalind.spring.ioc.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class PostConstructDemo {


    @PostConstruct
    private void init() {
        log.debug("postConstructDemo init");
    }

    public PostConstructDemo() {
        log.debug("postConstructDemo");
    }

    @PreDestroy
    private void destroy() {
        log.debug("postConstructDemo destroy");
    }
}
