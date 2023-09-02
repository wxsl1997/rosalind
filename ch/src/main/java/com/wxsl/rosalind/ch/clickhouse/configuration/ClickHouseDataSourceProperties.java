package com.wxsl.rosalind.ch.clickhouse.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wxsl1997
 */
@Data
@ConfigurationProperties(prefix = "clickhouse")
public class ClickHouseDataSourceProperties {

    String url = "jdbc:clickhouse://lysander.com:8123/rosalind_ch";

    String username;

    String password;

    Long timeout = 3600 * 1000L;
}
