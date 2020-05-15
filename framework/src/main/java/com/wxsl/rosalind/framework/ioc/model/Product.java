package com.wxsl.rosalind.framework.ioc.model;

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
    private Long id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品价格
     */
    private BigDecimal price;

    /**
     * 产品描述
     */
    private String desc;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 修改时间
     */
    private LocalDateTime modified;
}
