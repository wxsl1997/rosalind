package com.wxsl.rosalind.framework;

import com.wxsl.rosalind.framework.ioc.api.ImportBeanDefinitionRegistrarDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Slf4j
@Import(ImportBeanDefinitionRegistrarDemo.class)
@SpringBootApplication
public class FrameworkApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {

        log.info("started ...");
    }
}
