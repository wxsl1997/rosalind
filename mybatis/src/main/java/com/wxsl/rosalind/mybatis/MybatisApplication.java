package com.wxsl.rosalind.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MybatisApplication.class, args);
        log.info("web context:{}", context);
    }
}
