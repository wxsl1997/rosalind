package com.wxsl.rosalind.jpa.configuration;

import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Transactional(transactionManager = JpaDataSourceConfiguration.TX_MANAGER_NAME)
public @interface JpaTransactional {

    @AliasFor(value = "readOnly", annotation = Transactional.class)
    boolean readOnly() default false;
}
