package com.wxsl.rosalind.spring.ioc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    /**
     * 产品ID
     */
    Long id;

    /**
     * 产品名称
     */
    String name;

    /**
     * 产品价格
     */
    BigDecimal price;

    /**
     * 产品描述
     */
    String desc;

    /**
     * 创建时间
     */
    LocalDateTime created;

    /**
     * 修改时间
     */
    LocalDateTime modified;
}
