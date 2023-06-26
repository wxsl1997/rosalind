package com.wxsl.rosalind.zk.leader;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wxsl1997
 */
@Slf4j
@Disabled
@DisplayName("client")
class ClientTest {

    @Test
    void test() throws ExecutionException, InterruptedException {

        int corePoolSize = 12;
        int maxPoolSize = 12;
        int keepAliveTime = 0;
        // 线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new CustomizableThreadFactory("zk-worker-"));

        List<? extends Future<?>> futures = IntStream.rangeClosed(1, 8)
                .mapToObj(num -> (Runnable) () -> {

                    try (Client client = new Client("wxsl.com:2181,wxsl.com:2182,wxsl.com:2183")) {
                        // 初始化ZK
                        client.startZK();

                        while (!client.isConnected()) {
                            // 提交任务
                            client.submitTask(UUID.randomUUID().toString());
                            //noinspection BusyWait
                            Thread.sleep(15000L);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(executor::submit).collect(Collectors.toList());


        // 等待 client 工作
        for (Future<?> future : futures) {
            future.get();
        }

    }
}