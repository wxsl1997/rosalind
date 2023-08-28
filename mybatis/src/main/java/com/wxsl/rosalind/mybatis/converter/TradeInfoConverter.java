package com.wxsl.rosalind.mybatis.converter;

import com.wxsl.rosalind.mybatis.dto.TradeInfoDto;
import com.wxsl.rosalind.mybatis.entity.TradeInfo;
import org.mapstruct.Mapper;
import org.springframework.lang.Nullable;

/**
 * @author wxsl1997
 */
@Mapper
public interface TradeInfoConverter {

    @Nullable
    TradeInfoDto toTradeInfoDto(@Nullable TradeInfo tradeInfo);
}
