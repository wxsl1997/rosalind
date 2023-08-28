package com.wxsl.rosalind.mybatis.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    Long id;

    String name;

    ProductDescDto description;

    BigDecimal price;

    Integer stock;
}
