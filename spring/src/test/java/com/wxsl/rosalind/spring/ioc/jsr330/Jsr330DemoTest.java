package com.wxsl.rosalind.spring.ioc.jsr330;

import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Jsr330Demo")
class Jsr330DemoTest extends BaseTest {

    @Test
    @DisplayName("jsr330")
    void jsr330() {
        Jsr330Demo jsr330Demo = applicationContext.getBean("jsr330Demo", Jsr330Demo.class);
        Assertions.assertNotNull(jsr330Demo);
        Assertions.assertNotNull(jsr330Demo.getIPhone());
    }
}
