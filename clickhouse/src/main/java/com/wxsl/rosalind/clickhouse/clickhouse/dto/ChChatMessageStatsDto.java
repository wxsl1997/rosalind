package com.wxsl.rosalind.clickhouse.clickhouse.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChChatMessageStatsDto {

    String messageId;
    LocalDateTime dateTime;

    Boolean senderWwNick;
    Boolean syncTime;
    Boolean senderOpenUid;
    Boolean receiverWwNick;
    Boolean receiverOpenUid;
    Boolean senderMainAccountWwNick;
    Boolean senderMainAccountOpenUid;
    Boolean receiverMainAccountWwNick;
    Boolean receiverMainAccountOpenUid;
    Boolean sellerMainAccountWwNick;
    Boolean itemId;
    Boolean chatContent;
    Boolean wordNum;
    Boolean wordLength;

    Integer chatContentType;
    Integer messageType;

    String pageSource;
    String kitCode;
    String category;

    List<String> partnerBizid;

    public Object[] acquirePartnerBizid() {
        return getPartnerBizid().toArray();
    }
}
