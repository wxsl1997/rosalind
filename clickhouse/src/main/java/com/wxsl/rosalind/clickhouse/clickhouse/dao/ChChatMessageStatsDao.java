package com.wxsl.rosalind.clickhouse.clickhouse.dao;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.wxsl.rosalind.clickhouse.clickhouse.dto.ChChatMessageStatsDto;
import com.wxsl.rosalind.clickhouse.util.TimeUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChChatMessageStatsDao {

    JdbcTemplate jdbcTemplate;

    private static final ImmutableMap<String, Function<ChChatMessageStatsDto, Object>> CH_CHAT_MESSAGE_FILED_AND_FUNC_MAP = ImmutableMap.<String, Function<ChChatMessageStatsDto, Object>>builder()
            .put("`message_id`", ChChatMessageStatsDto::getMessageId)
            .put("`date_time`", d -> TimeUtils.format(d.getDateTime()))
            .put("`sync_time`", ChChatMessageStatsDto::getSyncTime)
            .put("`sender_ww_nick`", ChChatMessageStatsDto::getSenderWwNick)
            .put("`sender_open_uid`", ChChatMessageStatsDto::getSenderOpenUid)
            .put("`receiver_ww_nick`", ChChatMessageStatsDto::getReceiverWwNick)
            .put("`receiver_open_uid`", ChChatMessageStatsDto::getReceiverOpenUid)
            .put("`sender_main_account_ww_nick`", ChChatMessageStatsDto::getSenderMainAccountWwNick)
            .put("`sender_main_account_open_uid`", ChChatMessageStatsDto::getSenderMainAccountOpenUid)
            .put("`receiver_main_account_ww_nick`", ChChatMessageStatsDto::getReceiverMainAccountWwNick)
            .put("`receiver_main_account_open_uid`", ChChatMessageStatsDto::getReceiverMainAccountOpenUid)
            .put("`seller_main_account_ww_nick`", ChChatMessageStatsDto::getSellerMainAccountWwNick)
            .put("`item_id`", ChChatMessageStatsDto::getItemId)
            .put("`chat_content`", ChChatMessageStatsDto::getChatContent)
            .put("`word_num`", ChChatMessageStatsDto::getWordNum)
            .put("`word_length`", ChChatMessageStatsDto::getWordLength)
            .put("`chat_content_type`", ChChatMessageStatsDto::getChatContentType)
            .put("`message_type`", ChChatMessageStatsDto::getMessageType)
            .put("`partner_bizid`", ChChatMessageStatsDto::acquirePartnerBizid)
            .put("`page_source`", ChChatMessageStatsDto::getPageSource)
            .put("`kit_code`", ChChatMessageStatsDto::getKitCode)
            .put("`category`", ChChatMessageStatsDto::getCategory)

            .build();

    private static final List<String> CH_CHAT_MESSAGE_INSERT_FIELDS = ImmutableList.copyOf(CH_CHAT_MESSAGE_FILED_AND_FUNC_MAP.keySet());

    public void batchInsert(final List<ChChatMessageStatsDto> rows) {
        if (CollectionUtils.isEmpty(rows)) {
            return;
        }

        List<Object[]> args = batchInsertArgs(rows);

        String sql = insertSql();

        jdbcTemplate.batchUpdate(sql, args);
    }

    private static List<Object[]> batchInsertArgs(List<ChChatMessageStatsDto> rows) {
        List<Object[]> args = new ArrayList<>();
        for (ChChatMessageStatsDto row : rows) {
            Object[] values = new Object[CH_CHAT_MESSAGE_INSERT_FIELDS.size()];
            for (int index = 0; index < CH_CHAT_MESSAGE_INSERT_FIELDS.size(); index++) {
                values[index] = CH_CHAT_MESSAGE_FILED_AND_FUNC_MAP.get(CH_CHAT_MESSAGE_INSERT_FIELDS.get(index)).apply(row);
            }
            args.add(values);
        }
        return args;
    }

    private static String insertSql() {

        String fields = String.join(", ", CH_CHAT_MESSAGE_INSERT_FIELDS);

        String values = CH_CHAT_MESSAGE_INSERT_FIELDS.stream().map(s -> "?").collect(Collectors.joining(","));

        return String.format("INSERT INTO ch_chat_message_stat(%s) values(%s)", fields, values);
    }

}
