package com.wxsl.rosalind.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author wxsl1997
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoCreatedDto {

    Long productId;

    BigDecimal payment;

    Integer quantity;
}
