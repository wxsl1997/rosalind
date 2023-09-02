package com.wxsl.rosalind.ch.clickhouse.configuration;

import com.clickhouse.client.config.ClickHouseDefaults;
import com.clickhouse.jdbc.ClickHouseDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

/**
 * @author wxsl1997
 */
@Configuration
@EnableConfigurationProperties(ClickHouseDataSourceProperties.class)
public class ChDataSourceConfiguration {

    public static final String SOCKET_TIMEOUT = "socket_timeout";

    @Bean
    ClickHouseDataSource clickHouseDataSource(ClickHouseDataSourceProperties properties) throws SQLException {
        Properties config = new Properties();
        // username
        Optional.ofNullable(properties.getUsername()).filter(StringUtils::hasLength).ifPresent(value -> config.put(ClickHouseDefaults.USER.getKey(), value));
        // password
        Optional.ofNullable(properties.getPassword()).filter(StringUtils::hasLength).ifPresent(value -> config.put(ClickHouseDefaults.PASSWORD.getKey(), value));
        // socket_timeout
        config.put(SOCKET_TIMEOUT, properties.getTimeout());
        return new ClickHouseDataSource(properties.getUrl(), config);
    }

    @Bean
    public JdbcTemplate chJdbcTemplate(ClickHouseDataSource clickHouseDataSource) {
        return new JdbcTemplate(clickHouseDataSource);
    }
}
