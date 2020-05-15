package com.wxsl.rosalind.framework.ioc.api;

import com.wxsl.rosalind.framework.ioc.model.Product;
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
        //com.wxsl.rosalind.framework.ioc.api.TransactionalEventListenerDemo.eventListener
        applicationEventPublisher.publishEvent(event);
    }

    @Transactional
    void publishTxEvent(@NonNull Product event) {
        applicationEventPublisher.publishEvent(event);
    }
}
