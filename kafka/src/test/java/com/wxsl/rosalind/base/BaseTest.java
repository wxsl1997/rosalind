package com.wxsl.rosalind.base;

import com.wxsl.rosalind.TestMain;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@ContextConfiguration(classes = {TestMain.class})
@ExtendWith({SpringExtension.class})
@TestPropertySource(value = {"classpath:config/test.properties"})
public abstract class BaseTest {

    protected static ThreadPoolExecutor newThreadPoolExecutor(int threadNum, String threadNamePrefix) {

        int keepAliveTime = 0;
        // 线程池
        return new ThreadPoolExecutor(
                threadNum,
                threadNum,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new CustomizableThreadFactory(threadNamePrefix));
    }
}
