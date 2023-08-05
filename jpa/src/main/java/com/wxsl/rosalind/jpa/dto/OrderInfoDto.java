package com.wxsl.rosalind.jpa.dto;

import com.wxsl.rosalind.jpa.enumeration.OrderStatusEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wxsl1997
 */
@Data
public class OrderInfoDto {

    Long id;

    Long tradeId;

    Long productId;

    BigDecimal payment;

    OrderStatusEnum status;

    Integer quantity;
}