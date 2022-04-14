package com.wxsl.rosalind.framework.ioc.annotation;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class LookupDemo {

    @Lookup
    public abstract ScopeDemo getScopeDemo();
}
