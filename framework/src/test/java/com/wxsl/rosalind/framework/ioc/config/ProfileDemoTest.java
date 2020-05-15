package com.wxsl.rosalind.framework.ioc.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("dev")
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("ProfileDemo")
class ProfileDemoTest {

    @Autowired
    private ProfileDemo profileDemo;

    @Test
    @DisplayName("activeProfiles")
    void profileDemo() {
        Assertions.assertNotNull(profileDemo.getName());
        Assertions.assertNotNull(profileDemo.getCreated());
    }
}
