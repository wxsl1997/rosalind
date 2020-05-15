package com.wxsl.rosalind.framework;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BaseFrameWorkTest {

    protected AnnotationConfigApplicationContext applicationContext;

    @BeforeEach
    void init() {
        applicationContext = new AnnotationConfigApplicationContext(FrameworkApplication.class);
    }
}
