package com.wxsl.rosalind.kafka.producer;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.kafka.Interceptor.RosalindProducerInterceptor;
import com.wxsl.rosalind.kafka.configuration.KafkaPayload;
import com.wxsl.rosalind.kafka.serializer.WxslJsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.stream.IntStream;

/**
 * @author wxsl1997
 */
@Disabled
class KafkaProducerTest extends BaseTest {

    public static final int RECORD_NUM = 100;

    public static final String TOPIC = "rosalind-api-test-topic";

    @Resource
    KafkaProperties kafkaProperties;

    @Test
    public void send() {
        // kafka producer is thread safe
        try (KafkaProducer<String, Object> producer = new KafkaProducer<>(kafkaProducerConfig())) {
            IntStream.range(0, RECORD_NUM).forEach(num -> {
                Headers headers = new RecordHeaders().add("tag", "api".getBytes(StandardCharsets.UTF_8));
                KafkaPayload data = KafkaPayload.builder().time(LocalDateTime.now()).content("hello, Kafka!").build();
                ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC, null, null, null, data, headers);
                producer.send(record);

            });
        }
    }

    private Properties kafkaProducerConfig() {
        Properties properties = new Properties();
        // necessary properties
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // custom serializer
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, WxslJsonSerializer.class.getName());

        // optional properties
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "rosalind-producer-api-");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, RosalindProducerInterceptor.class.getName());

        return properties;
    }
}
