package com.wxsl.rosalind.ch;

import com.google.common.base.Stopwatch;
import com.wxsl.rosalind.ch.clickhouse.dao.ChChatMessageStatsSearchDao;
import com.wxsl.rosalind.ch.clickhouse.service.ChChatMessageStatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@SpringBootApplication
public class ChApplication implements ApplicationRunner, CommandLineRunner {

    @Resource
    ChChatMessageStatsService chatMessageStatsService;

    @Resource
    ChChatMessageStatsSearchDao chChatMessageStatsSearchDao;

    public static void main(String[] args) {
        SpringApplication.run(ChApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        int valueNum = 77;

        int[][] kpi = new int[valueNum][valueNum];

        int[] totalKpi = new int[valueNum];

        Stopwatch stopWatch = Stopwatch.createUnstarted();

        stopWatch.start();
        chChatMessageStatsSearchDao.scrollData(data -> {
            for (boolean[] values : data) {

                // kpi
                for (int i = 0; i < valueNum; i++) {

                    if (!values[i]) {
                        // 未满足前提条件
                        continue;
                    }

                    for (int j = 0; j < valueNum; j++) {
                        if (values[j]) {
                            kpi[i][j]++;
                        }
                    }
                }

                // total kpi
                for (int j = 0; j < valueNum; j++) {
                    if (values[j]) {
                        totalKpi[j]++;
                    }
                }
            }
        });

        System.out.println(Arrays.toString(totalKpi));

        for (int[] items : kpi) {
            System.out.println(Arrays.toString(items));
        }

        log.info("total cost, time:{}", stopWatch.elapsed(TimeUnit.MILLISECONDS));
        stopWatch.reset();
    }

    @Override
    public void run(String... args) throws Exception {

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
