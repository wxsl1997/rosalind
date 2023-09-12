package com.wxsl.rosalind.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxsl.rosalind.mybatis.configuration.AuditableEntity;
import com.wxsl.rosalind.mybatis.dto.ProductDescDto;
import com.wxsl.rosalind.mybatis.util.JsonTypeHandler;
import lombok.*;

import java.math.BigDecimal;

/**
 * 商品表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "product", autoResultMap = true)
public class Product extends AuditableEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    Long id;

    @TableField("name")
    String name;

    @TableField(value = "description", typeHandler = JsonTypeHandler.class)
    ProductDescDto description;

    @TableField("price")
    BigDecimal price;

    @TableField("stock")
    Integer stock;

    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    Long version;
}
