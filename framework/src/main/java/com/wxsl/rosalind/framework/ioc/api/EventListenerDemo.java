package com.wxsl.rosalind.framework.ioc.api;

import com.wxsl.rosalind.framework.ioc.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventListenerDemo {

    @Order
    @EventListener
    public void eventListener(Product product) {
        log.info(String.valueOf(product));
    }
}
