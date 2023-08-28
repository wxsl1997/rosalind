package com.wxsl.rosalind.spring.ioc.annotation;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class LookupDemo {

    @Lookup
    public abstract ScopeDemo getScopeDemo();
}
