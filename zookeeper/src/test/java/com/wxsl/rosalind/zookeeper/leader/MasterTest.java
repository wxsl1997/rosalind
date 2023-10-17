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
@DisplayName("master")
class MasterTest extends BaseTest {

    CuratorProperties curatorProperties;

    @Test
    void runForMaster() {

        int threadNum = 3;

        ThreadPoolExecutor executor = newThreadPoolExecutor(threadNum, "zk-master-");

        // 竞选任务
        List<Future<?>> futures = IntStream.rangeClosed(1, threadNum)
                .mapToObj(num -> (Runnable) () -> {

                    String candidate = "m" + num;

                    try (Master master = new Master(curatorProperties.getConnectString(), candidate)) {
                        // 初始化ZK
                        master.startZK();
                        // 初始化节点
                        master.bootstrap();
                        // 竞选主节点
                        master.runForMaster();
                        // 记录竞选结果
                        log.info("finish election for {}, master:{}", candidate, master);

                        while (!master.isExpired()) {
                            //noinspection BusyWait
                            Thread.sleep(1000L);
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