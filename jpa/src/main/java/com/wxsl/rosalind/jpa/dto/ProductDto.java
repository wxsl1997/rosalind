package com.wxsl.rosalind.jpa.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wxsl1997
 */
@Data
public class ProductDto {

    Long id;

    String name;

    ProductDescDto description;

    BigDecimal price;

    Integer stock;
}
