package com.wxsl.rosalind.kafka.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author wxsl1997
 */
@Slf4j
public class RosalindProducerInterceptor implements ProducerInterceptor<String, Object> {

    private static final LongAdder successNum = new LongAdder();
    private static final LongAdder failureNum = new LongAdder();


    @Override
    public ProducerRecord<String, Object> onSend(ProducerRecord<String, Object> record) {
        // add app header
        record.headers().add("app", "rosalind".getBytes(StandardCharsets.UTF_8));
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        // success metadata is not null
        if (Objects.nonNull(metadata)) {
            successNum.increment();
        }
        // otherwise exception is not null
        else {
            failureNum.increment();
        }
    }

    @Override
    public void close() {
        log.info("send success num:{}, failure message num:{}", successNum, failureNum);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
