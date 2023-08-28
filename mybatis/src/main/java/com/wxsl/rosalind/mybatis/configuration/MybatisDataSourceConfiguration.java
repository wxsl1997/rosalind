package com.wxsl.rosalind.mybatis.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.wxsl.rosalind.mybatis.mapper.UserMapper;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackageClasses = {UserMapper.class}, sqlSessionFactoryRef = MybatisDataSourceConfiguration.MYBATIS_SESSION_FACTORY)
@EnableTransactionManagement
public class MybatisDataSourceConfiguration {

    public static final String TX_MANAGER_NAME = "mybatisTransactionManager";

    public static final String MYBATIS_SESSION_FACTORY = "mybatisSessionFactory";

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = "rosalind.mybatis.datasource")
    public DataSource dataSource() {
        return new HikariDataSource();
    }

    @Bean(name = TX_MANAGER_NAME)
    public DataSourceTransactionManager mybatisTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
