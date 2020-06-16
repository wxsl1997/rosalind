package com.wxsl.rosalind.framework.jdbc.conifg;

import com.wxsl.rosalind.framework.jdbc.base.EnhancedJpaRepository;
import com.wxsl.rosalind.framework.jdbc.dao.TradeRateDao;
import com.wxsl.rosalind.framework.jdbc.model.TradeRate;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@EnableJpaRepositories(
        basePackageClasses = {TradeRateDao.class},
        repositoryBaseClass = EnhancedJpaRepository.class
)
@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
public class DataSourceConfigDemo {

    private static final String UNIT_NAME = "unit";

    @Bean
    @Primary
    @ConfigurationProperties("hikari.datasource")
    public DataSource dataSource() {
        return new HikariDataSource();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       DataSource dataSource,
                                                                       JpaProperties jpaProperties,
                                                                       HibernateProperties hibernateProperties) {
        return builder
                .dataSource(dataSource)
                .packages(TradeRate.class)
                .persistenceUnit(UNIT_NAME)
                .properties(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings()))
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
        transactionManager.setPersistenceUnitName(UNIT_NAME);
        return transactionManager;
    }
}
