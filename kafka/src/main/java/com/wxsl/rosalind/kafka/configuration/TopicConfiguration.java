package com.wxsl.rosalind.kafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wxsl1997
 */
@Configuration
public class TopicConfiguration {

    public static final String ROSALIND_TEST_TOPIC = "rosalind-test-topic";

    public static final String ROSALIND_TEST_TOPIC_DLT = "rosalind-test-topic.DLT";

    @Bean
    public NewTopic rosalindTestTopic() {
        return new NewTopic(ROSALIND_TEST_TOPIC, 8, (short) 2);
    }

    @Bean
    public NewTopic rosalindTestTopicDLT() {
        return new NewTopic(ROSALIND_TEST_TOPIC_DLT, 8, (short) 2);
    }
}
