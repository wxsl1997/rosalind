package com.wxsl.rosalind.zookeeper.curator;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.zookeeper.config.CuratorProperties;
import com.wxsl.rosalind.zookeeper.util.FutureUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.ExponentialBackoffRetry;
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
@DisplayName("curator")
class MasterTest extends BaseTest {

    CuratorProperties curatorProperties;

    @Test
    void runForMaster() {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(curatorProperties.getBaseSleepTimeMs(), curatorProperties.getMaxRetries());

        int threadNum = 3;

        ThreadPoolExecutor executor = newThreadPoolExecutor(threadNum, "zk-master-");

        // 竞选任务
        List<Future<?>> futures = IntStream.rangeClosed(1, threadNum)
                .mapToObj(num -> (Runnable) () -> {
                    String candidate = "m" + num;
                    try (Master master = new Master(curatorProperties.getConnectString(), candidate, retryPolicy)) {
                        master.startZK();
                        master.bootstrap();

                        // await for leadership
                        master.awaitLeadership();

                        while (master.isConnected()) {
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