package com.wxsl.rosalind.jpa.dto;

import com.wxsl.rosalind.jpa.enumeration.TradeStatusEnum;
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