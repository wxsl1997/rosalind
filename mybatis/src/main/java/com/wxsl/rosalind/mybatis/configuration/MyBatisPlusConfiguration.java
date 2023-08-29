package com.wxsl.rosalind.mybatis.configuration;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.wxsl.rosalind.mybatis.mapper.UserInfoMapper;

@Configuration
public class MyBatisPlusConfiguration implements MetaObjectHandler {

    public static final long DEFAULT_VERSION = 1L;

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage(UserInfoMapper.class.getPackage().getName());
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
        setFieldValByName("created", LocalDateTime.now(), metaObject);
        setFieldValByName("modified", LocalDateTime.now(), metaObject);
        setFieldValByName("version", DEFAULT_VERSION, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("modified", LocalDateTime.now(), metaObject);
    }
}
