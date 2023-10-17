package com.wxsl.rosalind.zookeeper.leader;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.zookeeper.config.CuratorProperties;
import com.wxsl.rosalind.zookeeper.util.FutureUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wxsl1997
 */
@Slf4j
@Disabled
@DisplayName("worker")
class WorkerTest extends BaseTest {

    CuratorProperties curatorProperties;

    @Test
    void register() {

        int threadNum = 8;

        ThreadPoolExecutor executor = newThreadPoolExecutor(threadNum, "zk-workers-");

        List<Future<?>> futures = IntStream.rangeClosed(1, 8)
                .mapToObj(num -> (Runnable) () -> {

                    String candidate = "w" + num;

                    try (Worker worker = new Worker(curatorProperties.getConnectString(), candidate)) {
                        // 初始化ZK
                        worker.startZK();
                        // 注册节点
                        worker.register();

                        // 初始化节点
                        worker.bootstrap();

                        // 处理任务
                        worker.assignTaskCallback();

                        while (!worker.isExpired()) {
                            //noinspection BusyWait
                            Thread.sleep(1000L);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(executor::submit)
                .collect(Collectors.toList());

        // wait for sub thread
        FutureUtils.runAll(futures);
    }
}