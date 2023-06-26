package com.wxsl.rosalind.zk.leader;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wxsl1997
 */
@Slf4j
@Disabled
@DisplayName("worker")
class WorkerTest {

    @Test
    void register() throws ExecutionException, InterruptedException {

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

                    String candidate = "w" + num;

                    try (Worker worker = new Worker("wxsl.com:2181,wxsl.com:2182,wxsl.com:2183", candidate)) {
                        // 初始化ZK
                        worker.startZK();
                        // 注册节点
                        worker.register();

                        // 初始化节点
                        worker.bootstrap();

                        // 处理任务
                        worker.processTask();

                        while (!worker.isConnected()) {
                            //noinspection BusyWait
                            Thread.sleep(1000L);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                })
                .map(executor::submit).collect(Collectors.toList());


        // 等待 worker 工作
        for (Future<?> future : futures) {
            future.get();
        }

    }

}