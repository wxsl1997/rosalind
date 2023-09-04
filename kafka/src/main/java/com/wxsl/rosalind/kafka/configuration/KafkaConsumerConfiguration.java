package com.wxsl.rosalind.kafka.configuration;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import java.util.List;
import java.util.Map;

import static com.wxsl.rosalind.kafka.configuration.TopicConfiguration.ROSALIND_TEST_TOPIC;
import static com.wxsl.rosalind.kafka.configuration.TopicConfiguration.ROSALIND_TEST_TOPIC_DLT;

@Slf4j
@Configuration
public class KafkaConsumerConfiguration {

    private static final String ROSALIND_CONSUMER_GROUP = "rosalind-consumer-group";

    private static final String ROSALIND_CONSUMER_DLT_GROUP = "rosalind-consumer-dlt-group";

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> containerFactory(ConsumerFactory<String, Object> consumerFactory, KafkaTemplate<String, Object> kafkaTemplate) {
        ConcurrentKafkaListenerContainerFactory<String, Object> container = new ConcurrentKafkaListenerContainerFactory<>();
        container.setConsumerFactory(consumerFactory);
        container.setBatchListener(true);
        container.setConcurrency(4);
        container.setReplyTemplate(kafkaTemplate);
        container.setCommonErrorHandler(new DefaultErrorHandler(new DeadLetterPublishingRecoverer(kafkaTemplate), new FixedBackOff(1000, 3)));
        return container;
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> config = Maps.newHashMap();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, RosalindJsonDeserializer.class);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 512);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        //config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> deadLetterContainerFactory(ConsumerFactory<String, Object> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Object> container = new ConcurrentKafkaListenerContainerFactory<>();
        container.setConsumerFactory(consumerFactory);
        container.setBatchListener(true);
        container.setConcurrency(4);
        container.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(1000, 1)));
        return container;
    }

    @KafkaListener(clientIdPrefix = "rosalind-consumer-", groupId = ROSALIND_CONSUMER_GROUP, topics = ROSALIND_TEST_TOPIC, containerFactory = "containerFactory")
    public void consumer(List<ConsumerRecord<String, String>> records) {
        log.info("start consume kafka message, size: {}", records.size());
        //throw new RuntimeException("consume kafka message failed");
    }

    @KafkaListener(clientIdPrefix = "rosalind-dlt-consumer-", groupId = ROSALIND_CONSUMER_DLT_GROUP, topics = ROSALIND_TEST_TOPIC_DLT, containerFactory = "deadLetterContainerFactory")
    public void dltConsumer(List<ConsumerRecord<String, String>> records) {
        log.info("start consume dead letter kafka message, size: {}", records.size());
        //throw new RuntimeException("consume kafka dead letter message failed");
    }
}
