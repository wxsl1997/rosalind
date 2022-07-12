package com.wxsl.rosalind;

import com.wxsl.rosalind.framework.ioc.api.ImportBeanDefinitionRegistrarDemo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Import(ImportBeanDefinitionRegistrarDemo.class)
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class TestMain {

    @Bean
    @Primary
    @Profile("unit")
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
}
