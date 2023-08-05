package com.wxsl.rosalind.jpa.mapping;

import com.wxsl.rosalind.jpa.dto.TradeInfoDto;
import com.wxsl.rosalind.jpa.model.TradeInfo;
import org.mapstruct.Mapper;

/**
 * @author wxsl1997
 */
@Mapper
public interface TradeInfoMapper {
    TradeInfoDto toTradeInfoDto(TradeInfo tradeInfo);
}
