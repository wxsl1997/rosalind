package com.wxsl.rosalind.security.catalog.config;

import com.wxsl.rosalind.security.catalog.dao.UserDao;
import com.wxsl.rosalind.security.catalog.model.User;
import com.wxsl.rosalind.security.jpa.EnhancedJpaRepository;
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
        transactionManagerRef = CatalogRepositoryConfiguration.CATALOG_TRANSACTION_MANAGER,
        entityManagerFactoryRef = "catalogEntityManagerFactory",
        basePackageClasses = {UserDao.class},
        repositoryBaseClass = EnhancedJpaRepository.class
)
@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
public class CatalogRepositoryConfiguration {

    private static final String UNIT_NAME = "catalog";

    public static final String CATALOG_TRANSACTION_MANAGER = "catalogTransactionManager";

    @Bean
    @ConfigurationProperties("hikari.datasource")
    public DataSource dataSource() {
        return new HikariDataSource();
    }

    @Bean(CATALOG_TRANSACTION_MANAGER)
    public PlatformTransactionManager catalogTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
        transactionManager.setPersistenceUnitName(UNIT_NAME);
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean catalogEntityManagerFactory(EntityManagerFactoryBuilder builder,
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
}
