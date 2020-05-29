package com.wxsl.rosalind.framework.aop.config;

import com.wxsl.rosalind.framework.aop.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class UserConfigDemo {

    @Bean
    public User hermia() {
        LocalDateTime time = LocalDateTime.now();
        return User.builder()
                .id(20002L)
                .name("赫米娅")
                .account("hermia")
                .password("hermia")
                .created(time)
                .modified(time)
                .build();
    }
}
