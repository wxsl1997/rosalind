package com.wxsl.rosalind.framework.ioc.annotation;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class LookupDemo {

    @Lookup
    ScopeDemo getScopeDemo() {
        // override dynamically
        return null;
    }
}
