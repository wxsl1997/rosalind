package com.wxsl.rosalind.framework;

import com.wxsl.rosalind.framework.ioc.api.ImportBeanDefinitionRegistrarDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;


@Import(ImportBeanDefinitionRegistrarDemo.class)
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class FrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkApplication.class, args);
    }
}
