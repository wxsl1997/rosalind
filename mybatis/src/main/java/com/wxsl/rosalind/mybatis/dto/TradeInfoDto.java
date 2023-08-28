package com.wxsl.rosalind.mybatis.dto;

import com.wxsl.rosalind.mybatis.enumeration.TradeStatusEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wxsl1997
 */
@Data
public class TradeInfoDto {

    Long id;

    Long userId;

    BigDecimal payment;

    TradeStatusEnum status;
}