package com.wxsl.rosalind.mybatis.configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.wxsl.rosalind.mybatis.mapper.UserMapper;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class MyBatisPlusConfiguration implements MetaObjectHandler {

    public static final long DEFAULT_VERSION = 1L;

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage(UserMapper.class.getPackage().getName());
        return scannerConfigurer;
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime created = (LocalDateTime) getFieldValByName("created", metaObject);
        LocalDateTime modified = (LocalDateTime) getFieldValByName("modified", metaObject);
        Long version = (Long) getFieldValByName("version", metaObject);
        if (created == null) {
            setFieldValByName("created", LocalDateTime.now(), metaObject);
        }
        if (modified == null) {
            setFieldValByName("modified", LocalDateTime.now(), metaObject);
        }
        if (version == null) {
            setFieldValByName("version", DEFAULT_VERSION, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime updateTime = (LocalDateTime) getFieldValByName("modified", metaObject);
        if (updateTime == null) {
            setFieldValByName("modified", LocalDateTime.now(), metaObject);
        }
    }
}
