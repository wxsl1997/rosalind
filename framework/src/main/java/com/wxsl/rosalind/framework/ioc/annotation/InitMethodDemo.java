package com.wxsl.rosalind.framework.ioc.annotation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitMethodDemo {

    public InitMethodDemo() {
        log.debug("initMethodDemo");
    }

    private void init() {
        log.debug("initMethodDemo init");
    }

    private void destroy() {
        log.debug("initMethodDemo destroy");
    }
}
