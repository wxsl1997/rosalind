package com.wxsl.rosalind.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product")
public class Product implements Serializable {

    static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    Long id;

    @TableField("name")
    String name;

    @TableField("description")
    String description;

    @TableField("price")
    BigDecimal price;

    @TableField("stock")
    Integer stock;

    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    Long version;

    @TableField(value = "created", fill = FieldFill.INSERT)
    LocalDateTime created;

    @TableField(value = "modified", fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modified;
}
