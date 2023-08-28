package com.wxsl.rosalind.mybatis.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxsl1997
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInsertDto {

    String name;

    ProductDescDto description;

    BigDecimal price;

    Integer stock;
}
