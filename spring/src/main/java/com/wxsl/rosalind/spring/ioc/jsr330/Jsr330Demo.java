package com.wxsl.rosalind.spring.ioc.jsr330;

import com.wxsl.rosalind.spring.ioc.model.Product;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Getter
@Singleton
@Named
public class Jsr330Demo {

    final Product iPhone;

    @Inject
    @Named("iPhone")
    public Jsr330Demo(Product iPhone) {
        this.iPhone = iPhone;
    }
}
