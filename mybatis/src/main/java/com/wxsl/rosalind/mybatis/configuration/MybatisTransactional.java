package com.wxsl.rosalind.mybatis.configuration;

import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Transactional(transactionManager = MybatisDataSourceConfiguration.TX_MANAGER_NAME)
public @interface MybatisTransactional {

    @AliasFor(value = "readOnly", annotation = Transactional.class)
    boolean readOnly() default false;
}
