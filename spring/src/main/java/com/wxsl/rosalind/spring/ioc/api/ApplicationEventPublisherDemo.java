package com.wxsl.rosalind.spring.ioc.api;

import com.wxsl.rosalind.spring.ioc.model.Product;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ApplicationEventPublisherDemo {

    ApplicationEventPublisher applicationEventPublisher;

    void publishEvent(@NonNull Product event) {
        applicationEventPublisher.publishEvent(event);
    }

    @Transactional
    public void publishTxEvent(@NonNull Product event) {
        applicationEventPublisher.publishEvent(event);
    }
}
