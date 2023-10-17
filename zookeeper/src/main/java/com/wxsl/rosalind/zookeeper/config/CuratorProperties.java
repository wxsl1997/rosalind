package com.wxsl.rosalind.zookeeper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wxsl1997
 */
@Data
@Component
@ConfigurationProperties("curator")
public class CuratorProperties {

    String connectString = "wxsl.com:2181,wxsl.com:2182,wxsl.com:2183";

    Integer baseSleepTimeMs = 1000;

    Integer maxRetries = 3;
}
