package com.wxsl.rosalind.ch.clickhouse.service;

import com.google.common.base.Stopwatch;
import com.wxsl.rosalind.ch.clickhouse.dao.ChChatMessageStatsDao;
import com.wxsl.rosalind.ch.clickhouse.dto.ChChatMessageStatsDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChChatMessageStatsService {


    ChChatMessageStatsDao chChatMessageStatsDao;


    public void run() {

        int round = 1_000;
        int capacity = 100_000;

        List<ChChatMessageStatsDto> data = new ArrayList<>(capacity);
        Stopwatch stopWatch = Stopwatch.createUnstarted();
        for (int i = 1; i <= round; i++) {

            stopWatch.start();

            IntStream.range(0, capacity).forEach(num -> data.add(createChChatMessageStatsDto()));

            chChatMessageStatsDao.batchInsert(data);

            log.info("success insert ch message, time:{}, round:{}", stopWatch.elapsed(TimeUnit.MILLISECONDS), i);

            stopWatch.reset();

            data.clear();
        }
    }

    private static ChChatMessageStatsDto createChChatMessageStatsDto() {

        ChChatMessageStatsDto row = new ChChatMessageStatsDto();
        row.setMessageId(System.nanoTime() + ".PNM");
        row.setDateTime(LocalDateTime.now().minusDays((long) (Math.random() * 4)));

        boolean prob = Math.random() > 0.8;

        row.setSenderWwNick(prob);
        row.setSenderOpenUid(prob);
        row.setReceiverWwNick(prob);
        row.setSyncTime(prob);
        row.setReceiverOpenUid(prob);
        row.setSenderMainAccountWwNick(prob);
        row.setSenderMainAccountOpenUid(prob);
        row.setReceiverMainAccountWwNick(prob);
        row.setReceiverMainAccountOpenUid(prob);
        row.setSellerMainAccountWwNick(prob);
        row.setItemId(prob);
        row.setChatContent(prob);
        row.setWordNum(prob);
        row.setWordLength(prob);

        row.setChatContentType((int) (Math.random() * 4));
        row.setMessageType((int) (Math.random() * 4));

        row.setPartnerBizid(IntStream.range(0, (int) (5 * Math.random())).mapToObj(n -> "partnerBizid" + n).collect(Collectors.toList()));
        row.setPageSource(prob ? "pageSource-1" : "pageSource-2");
        row.setKitCode(prob ? "kitCode-1" : "kitCode-2");
        row.setCategory(prob ? "category-1" : "category-2");

        return row;
    }

}
