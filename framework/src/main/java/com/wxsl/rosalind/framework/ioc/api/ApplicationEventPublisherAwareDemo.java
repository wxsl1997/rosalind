package com.wxsl.rosalind.framework.ioc.api;

import lombok.Getter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApplicationEventPublisherAwareDemo implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    void publishEvent(@NonNull Object event) {
        applicationEventPublisher.publishEvent(event);
    }
}
