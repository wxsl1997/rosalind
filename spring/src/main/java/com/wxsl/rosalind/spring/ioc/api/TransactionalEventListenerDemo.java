package com.wxsl.rosalind.spring.ioc.api;

import com.wxsl.rosalind.spring.ioc.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class TransactionalEventListenerDemo {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void eventListener(Product product) {
        log.info(String.valueOf(product));
    }
}
