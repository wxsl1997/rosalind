package com.wxsl.rosalind.kafka.configuration;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.kafka.util.FutureUtils;
import com.wxsl.rosalind.kafka.util.ThreadUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.wxsl.rosalind.kafka.configuration.TopicConfiguration.ROSALIND_TEST_TOPIC;

/**
 * @author wxsl1997
 */
@Disabled
class KafkaProducerConfigurationTest extends BaseTest {

    private static final Integer RECORD_NUM = 1024;

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    public void send() {

        int threadNum = 8;

        ThreadPoolExecutor executor = newThreadPoolExecutor(threadNum, "kafka-producer-");

        List<Future<?>> futures = IntStream.rangeClosed(1, threadNum)
                .mapToObj(index -> (Runnable) () -> {
                    IntStream.range(0, RECORD_NUM).forEach(num -> {
                        Headers headers = new RecordHeaders().add("tag", "v1".getBytes(StandardCharsets.UTF_8));
                        KafkaPayload data = KafkaPayload.builder().type("test").content(UUID.randomUUID().toString()).time(LocalDateTime.now()).build();
                        ProducerRecord<String, Object> record = new ProducerRecord<>(ROSALIND_TEST_TOPIC, null, null, null, data, headers);
                        kafkaTemplate.send(record);
                    });
                    ThreadUtils.sleep(100L, TimeUnit.MILLISECONDS);
                })
                .map(executor::submit).collect(Collectors.toList());

        // wait for sub thread
        FutureUtils.runAll(futures);

        ThreadUtils.sleep(10L, TimeUnit.SECONDS);
    }
}