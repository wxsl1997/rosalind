package com.wxsl.rosalind.zookeeper.config;

import com.google.common.collect.Lists;
import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.zookeeper.util.FutureUtils;
import com.wxsl.rosalind.zookeeper.util.ThreadUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wxsl1997
 */
@Slf4j
@Disabled
@DisplayName("lock")
class ZkDistributedLockTest extends BaseTest {

    @Resource
    CuratorFramework curatorFramework;

    @Test
    public void interProcessMutex() {

        int threadNum = 8;

        ThreadPoolExecutor executor = newThreadPoolExecutor(threadNum, "zk-lock-");

        String path = "/curator/lock/user-1";

        List<Future<?>> futures = IntStream.rangeClosed(1, threadNum)
                .mapToObj(num -> (Runnable) () -> {
                    /*
                     * InterProcessMutex: reentry
                     * InterProcessSemaphoreMutex: unable reentry
                     * InterProcessReadWriteLock: read and read coexist, otherwise mutex
                     */

                    InterProcessMutex lock = new InterProcessMutex(curatorFramework, path);
                    try {
                        boolean acquire = lock.acquire(2, TimeUnit.SECONDS);
                        log.info("acquire inter process mutex path:{}, success:{}", path, acquire);
                        ThreadUtils.sleep(1, TimeUnit.SECONDS);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        try {
                            if (lock.isAcquiredInThisProcess()) {
                                lock.release();
                                log.info("successful release process mutex path:{}", path);
                            }
                        } catch (Exception e) {
                            log.error("release inter process mutex failed path:{}", path, e);
                        }
                    }
                })
                .map(executor::submit).collect(Collectors.toList());

        // wait for sub thread
        FutureUtils.runAll(futures, e -> log.error("error occur in sub thread", e));
    }

    @Test
    public void interProcessMultiLock() {
        int threadNum = 8;

        ThreadPoolExecutor executor = newThreadPoolExecutor(threadNum, "zk-lock-");

        List<String> paths = Lists.newArrayList("/curator/lock/user-1", "/curator/lock/user-2");

        List<Future<?>> futures = IntStream.rangeClosed(1, threadNum)
                .mapToObj(num -> (Runnable) () -> {

                    InterProcessMultiLock lock = new InterProcessMultiLock(curatorFramework, paths);
                    try {
                        boolean acquire = lock.acquire(2, TimeUnit.SECONDS);
                        log.info("acquire inter process mutex path:{}, success:{}", paths, acquire);
                        ThreadUtils.sleep(1, TimeUnit.SECONDS);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        try {
                            if (lock.isAcquiredInThisProcess()) {
                                lock.release();
                                log.info("successful release process mutex path:{}", paths);
                            }
                        } catch (Exception e) {
                            log.error("release inter process mutex failed path:{}", paths, e);
                        }
                    }
                })
                .map(executor::submit).collect(Collectors.toList());

        // wait for sub thread
        FutureUtils.runAll(futures, e -> log.error("error occur in sub thread", e));
    }
}