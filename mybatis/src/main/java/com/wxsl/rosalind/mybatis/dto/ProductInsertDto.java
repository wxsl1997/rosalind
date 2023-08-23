package com.wxsl.rosalind.mybatis.dto;

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
public class ProductInsertDto {

    String name;

    String description;

    BigDecimal price;

    Integer stock;
}
