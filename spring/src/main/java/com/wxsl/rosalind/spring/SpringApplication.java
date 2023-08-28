package com.wxsl.rosalind.spring;

import com.wxsl.rosalind.spring.ioc.api.ImportBeanDefinitionRegistrarDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Slf4j
@Import(ImportBeanDefinitionRegistrarDemo.class)
@SpringBootApplication
public class SpringApplication implements ApplicationRunner {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {

        log.info("started ...");
    }
}
