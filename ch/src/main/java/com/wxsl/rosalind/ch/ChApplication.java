package com.wxsl.rosalind.ch;

import com.wxsl.rosalind.ch.clickhouse.service.ChChatMessageStatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@SpringBootApplication
public class ChApplication implements ApplicationRunner {

    @Resource
    ChChatMessageStatsService chatMessageStatsService;

    public static void main(String[] args) {
        SpringApplication.run(ChApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int corePoolSize = 8;
        int maxPoolSize = 8;
        int keepAliveTime = 0;
        // 线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new CustomizableThreadFactory("ch-run-"));

        // 执行任务
        List<? extends Future<?>> futures = IntStream.range(0, corePoolSize)
                .mapToObj(num -> (Runnable) () -> chatMessageStatsService.run())
                .map(executor::submit)
                .collect(Collectors.toList());

        // 等待 master 工作
        for (Future<?> future : futures) {
            future.get();
        }
    }
}
