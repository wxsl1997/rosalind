package com.wxsl.rosalind.framework.ioc.config;

import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfileDemoTest extends BaseTest {

    @Test
    @DisplayName("activeProfiles")
    void profileDemo() {
        ProfileDemo profileDemo = applicationContext().getBean("profileDemo", ProfileDemo.class);
        Assertions.assertNotNull(profileDemo.getName());
        Assertions.assertNotNull(profileDemo.getCreated());
    }
}
