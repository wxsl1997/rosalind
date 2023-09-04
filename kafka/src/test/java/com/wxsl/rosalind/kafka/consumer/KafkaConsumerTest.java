package com.wxsl.rosalind.kafka.consumer;

import com.google.common.collect.Lists;
import com.wxsl.rosalind.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wxsl1997
 */
@Slf4j
@Disabled
class KafkaConsumerTest extends BaseTest {

    public static final String TOPIC = "rosalind-api-test-topic";

    @Resource
    KafkaProperties kafkaProperties;

    private final AtomicBoolean running = new AtomicBoolean(true);


    @Test
    public void poll() {
        Properties properties = kafkaConsumerConfig();
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {

            consumer.subscribe(Lists.newArrayList(TOPIC));

            while (running.get()) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));

                for (TopicPartition partition : consumerRecords.partitions()) {
                    List<ConsumerRecord<String, String>> records = consumerRecords.records(partition);
                    for (ConsumerRecord<String, String> record : records) {
                        log.info("consume rosalind api message:{}", record);
                    }
                }
                // commit sync
                consumer.commitSync();
            }
        }
    }

    @Test
    public void assign() {
        Properties properties = kafkaConsumerConfig();
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {

            // just accept partitions zero, exclusive with subscribe
            TopicPartition topicPartition = new TopicPartition(TOPIC, 0);
            consumer.assign(Lists.newArrayList(topicPartition));

            // obtain all partition for topic
            // consumer.partitionsFor(TOPIC);

            log.info("next record offset is:{}", consumer.position(topicPartition));

            while (running.get()) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> record : consumerRecords) {
                    log.info("consume rosalind api message:{}", record);
                }
                // commit sync
                consumer.commitSync();
            }

        }
    }


    @Test
    public void seek() {
        Properties properties = kafkaConsumerConfig();
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {

            TopicPartition topicPartition = new TopicPartition(TOPIC, 0);
            consumer.assign(Lists.newArrayList(topicPartition));


            Set<TopicPartition> assignment = Sets.newHashSet();
            while (assignment.isEmpty()) {
                // require execute poll before execute seek
                consumer.poll(Duration.ofMillis(100));


                // acquire consumer allocated partition
                assignment = consumer.assignment();
                for (TopicPartition partition : assignment) {
                    // assign next record position for partition
                    consumer.seek(partition, 0);
                }
            }


            while (running.get()) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> record : consumerRecords) {
                    log.info("consume rosalind api message:{}", record);
                }
                // commit sync
                consumer.commitSync();
            }

        }
    }

    private Properties kafkaConsumerConfig() {
        Properties properties = new Properties();
        // necessary properties
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "rosalind-consumer-api-group");
        // optional properties
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "rosalind-consumer-api-");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        properties.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1);
        return properties;
    }
}
