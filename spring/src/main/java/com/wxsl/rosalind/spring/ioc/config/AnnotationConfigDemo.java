package com.wxsl.rosalind.spring.ioc.config;

import com.wxsl.rosalind.spring.ioc.annotation.InitMethodDemo;
import com.wxsl.rosalind.spring.ioc.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class AnnotationConfigDemo {

    @Bean
    public Product iPhone() {
        LocalDateTime time = LocalDateTime.now();
        return Product.builder()
                .id(10001L)
                .name("iPhone 11")
                .price(BigDecimal.valueOf(5999L))
                .desc("iPhone 11 显示屏采用曲线优美的圆角设计，四个圆角位于一个标准矩形内。")
                .created(time)
                .modified(time)
                .build();
    }

    @Bean
    public Product airPods() {
        LocalDateTime time = LocalDateTime.now();
        return Product.builder()
                .id(10002L)
                .name("AirPods Pro")
                .price(BigDecimal.valueOf(1999L))
                .desc("让你的 AirPods 更显个性，免费镌刻服务，Apple 独家福利。")
                .created(time)
                .modified(time)
                .build();
    }

    @Bean
    public Product macBook() {
        LocalDateTime time = LocalDateTime.now();
        return Product.builder()
                .id(10003L)
                .name("MacBook Pro")
                .price(BigDecimal.valueOf(22199L))
                .desc("动力，行动力。")
                .created(time)
                .modified(time)
                .build();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public InitMethodDemo initMethodDemo() {
        return new InitMethodDemo();
    }
}
