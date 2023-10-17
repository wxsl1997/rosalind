package com.wxsl.rosalind.zookeeper.leader;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.zookeeper.config.CuratorProperties;
import com.wxsl.rosalind.zookeeper.util.FutureUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wxsl1997
 */
@Slf4j
@Disabled
@DisplayName("client")
class ClientTest extends BaseTest {

    CuratorProperties curatorProperties;

    @Test
    void test()  {

        int threadNum = 8;

        ThreadPoolExecutor executor = newThreadPoolExecutor(threadNum, "zk-client-");

        List<Future<?>> futures = IntStream.rangeClosed(1, threadNum)
                .mapToObj(num -> (Runnable) () -> {

                    try (Client client = new Client(curatorProperties.getConnectString())) {
                        // 初始化ZK
                        client.startZK();

                        while (!client.isExpired()) {
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

        // wait for sub thread
        FutureUtils.runAll(futures);
    }
}