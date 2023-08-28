package com.wxsl.rosalind.spring.ioc.annotation;

import com.wxsl.rosalind.spring.ioc.model.Product;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Getter
@Component
@DependsOn("iPhone")
public class DependOnDemo implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    Product iPhone() {
        return applicationContext.getBean("iPhone", Product.class);
    }
}
