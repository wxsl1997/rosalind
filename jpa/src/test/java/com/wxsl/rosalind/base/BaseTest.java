package com.wxsl.rosalind.base;

import com.wxsl.rosalind.TestMain;
import com.wxsl.rosalind.jpa.configuration.JpaTransactional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

@ContextConfiguration(classes = {TestMain.class})
@ExtendWith({SpringExtension.class})
@TestPropertySource(value = {"classpath:config/test.properties"})
@JpaTransactional
public abstract class BaseTest {

    @Bean
    @Profile("mock")
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
}
