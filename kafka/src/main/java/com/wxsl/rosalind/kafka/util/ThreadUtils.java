package com.wxsl.rosalind.kafka.util;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author wxsl1997
 */
public class ThreadUtils {

    @SneakyThrows
    public static void sleep(long time, TimeUnit unit) {
        Thread.sleep(unit.toMillis(time));
    }
}
