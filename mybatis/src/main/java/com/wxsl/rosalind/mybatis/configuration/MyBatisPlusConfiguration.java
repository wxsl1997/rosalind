package com.wxsl.rosalind.mybatis.configuration;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
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
        // TODO 该乐观锁属于更新失败, 非乐观锁异常, 区别于 hibernate 乐观锁异常
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

    /**
     * @see MybatisSqlSessionFactoryBuilder#build(org.apache.ibatis.session.Configuration)
     * @see IdWorker
     */
    @Bean
    IdentifierGenerator identifierGenerator() {

        // snowflake 结构: 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000

        // 第 1 位标识位 + 41位 时间戳 + 5 位数据中心ID + 5 位工作机器ID + 12 位序列位

        // TODO 根据需求获取该值
        int dataCenterId = 0;
        int workerId = 0;
        return new DefaultIdentifierGenerator(workerId, dataCenterId);
    }
}
