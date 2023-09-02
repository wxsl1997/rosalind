package com.wxsl.rosalind.ch.clickhouse.dao;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Slf4j
@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChChatMessageStatsSearchDao {

    JdbcTemplate jdbcTemplate;


    @SneakyThrows
    public void stats() {
        Scanner scanner = new Scanner(new File("/Users/wxsl1997/code/tech/rosalind/ch/src/main/resources/col-6400.sql"));
        StringBuilder sql = new StringBuilder();
        while (scanner.hasNext()) {
            sql.append(" ").append(scanner.next());
        }


        String enhance = "set max_ast_elements=500000, max_query_size=10737418240, max_memory_usage=21474836480, max_execution_time=1800;" + sql;

        jdbcTemplate.query(enhance, (ResultSetExtractor<Object>) rs -> {
            ResultSetMetaData metaData = rs.getMetaData();
            System.out.println(metaData);
            return metaData;
        });
    }

    public void scrollData(Consumer<List<boolean[]>> callback) throws FileNotFoundException {


        Scanner scanner = new Scanner(new File("/Users/wxsl1997/code/tech/rosalind/ch/src/main/resources/col-stream.sql"));

        StringBuilder sql = new StringBuilder();
        while (scanner.hasNext()) {
            if (sql.length() > 0) {
                sql.append(" ");
            }
            sql.append(scanner.next());
        }

        jdbcTemplate.query(sql.toString(), (ResultSetExtractor<List<boolean[]>>) rs -> {

            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();


            int count = 0;

            int batchSize = 100000;
            List<boolean[]> dataSlice = new ArrayList<>(batchSize);

            Stopwatch stopWatch = Stopwatch.createUnstarted();

            int page = 0;

            while (rs.next()) {

                boolean[] values = new boolean[columnCount];
                for (int index = 1; index <= columnCount; index++) {
                    values[index - 1] = rs.getBoolean(index);
                }
                dataSlice.add(values);

                if (++count % batchSize == 0) {
                    stopWatch.start();
                    callback.accept(dataSlice);
                    dataSlice.clear();
                    log.info("success consumer data, page:{}, time:{}", ++page, stopWatch.elapsed(TimeUnit.MILLISECONDS));
                    stopWatch.reset();

                }
            }

            callback.accept(dataSlice);
            dataSlice.clear();

            return Lists.newArrayList();
        });
    }
}
