package com.wxsl.rosalind.zookeeper.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wxsl1997
 */
@Configuration
public class CuratorConfiguration {

    @Bean(initMethod = "start", destroyMethod = "close")
    CuratorFramework curatorFramework(CuratorProperties properties) {
        return CuratorFrameworkFactory.builder()
                .connectString(properties.getConnectString())
                .retryPolicy(new ExponentialBackoffRetry(properties.getBaseSleepTimeMs(), properties.getMaxRetries()))
                .build();
    }

}
