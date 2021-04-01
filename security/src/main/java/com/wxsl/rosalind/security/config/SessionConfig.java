package com.wxsl.rosalind.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

@Configuration
@EnableRedisHttpSession
public class SessionConfig {

    @Bean
    SpringSessionBackedSessionRegistry<? extends Session> springSessionBackedSessionRegistry(FindByIndexNameSessionRepository<? extends Session> findByIndexNameSessionRepository) {
        return new SpringSessionBackedSessionRegistry<>(findByIndexNameSessionRepository);
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
