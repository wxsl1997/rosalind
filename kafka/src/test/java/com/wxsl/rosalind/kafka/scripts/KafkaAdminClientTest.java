package com.wxsl.rosalind.kafka.scripts;

import com.wxsl.rosalind.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.TopicConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author wxsl1997
 */

@Slf4j
@Disabled
public class KafkaAdminClientTest extends BaseTest {

    public static final String TOPIC = "admin-client-verify-topic";
    @Resource
    KafkaProperties kafkaProperties;

    @Test
    void describeTopic() throws ExecutionException, InterruptedException {

        try (AdminClient client = AdminClient.create(clientConfig())) {

            DescribeTopicsResult result = client.describeTopics(Collections.singleton(TOPIC));

            Map<String, TopicDescription> config = result.all().get();

            log.info("describe topic, topic:{}, config:{}", TOPIC, config);
        }
    }


    @Test
    void createTopic() {

        try (AdminClient client = AdminClient.create(clientConfig())) {

            NewTopic newTopic = new NewTopic(TOPIC, 8, (short) 2);

            Map<String, String> configs = new HashMap<>();
            configs.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT);
            newTopic.configs(configs);

            client.createTopics(Collections.singleton(newTopic));
        }
    }

    private Properties clientConfig() {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        return props;
    }
}
