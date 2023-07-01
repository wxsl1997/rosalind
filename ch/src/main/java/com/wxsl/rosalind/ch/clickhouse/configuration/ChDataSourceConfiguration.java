package com.wxsl.rosalind.ch.clickhouse.configuration;

import com.clickhouse.jdbc.ClickHouseDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

/**
 * @author wxsl1997
 */
@Configuration
public class ChDataSourceConfiguration {

    @Bean
    ClickHouseDataSource clickHouseDataSource() throws SQLException {
        String uri = "jdbc:clickhouse://lysander.com:8123/rosalind_ch";
        return new ClickHouseDataSource(uri);
    }

    @Bean
    public JdbcTemplate chJdbcTemplate(ClickHouseDataSource clickHouseDataSource) {
        return new JdbcTemplate(clickHouseDataSource);
    }
}
