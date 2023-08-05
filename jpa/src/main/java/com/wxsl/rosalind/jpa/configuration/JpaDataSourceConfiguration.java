package com.wxsl.rosalind.jpa.configuration;

import com.wxsl.rosalind.jpa.model.User;
import com.wxsl.rosalind.jpa.repository.UserRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@EnableJpaRepositories(
        basePackageClasses = {UserRepository.class},
        repositoryBaseClass = EnhancedJpaRepository.class,
        transactionManagerRef = JpaDataSourceConfiguration.TX_MANAGER_NAME
)
@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
public class JpaDataSourceConfiguration {

    private static final String UNIT_NAME = "rosalind";

    public static final String TX_MANAGER_NAME = "jpaTransactionManager";

    @Bean
    @ConfigurationProperties(prefix = "rosalind.jpa.datasource")

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
                .packages(User.class)
                .persistenceUnit(UNIT_NAME)
                .properties(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings()))
                .build();
    }

    @Bean(name = TX_MANAGER_NAME)
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
        transactionManager.setPersistenceUnitName(UNIT_NAME);
        return transactionManager;
    }
}
