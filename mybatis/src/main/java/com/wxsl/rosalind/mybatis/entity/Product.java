package com.wxsl.rosalind.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.wxsl.rosalind.mybatis.dto.ProductDescDto;
import com.wxsl.rosalind.mybatis.util.JsonTypeHandler;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "product", autoResultMap = true)
public class Product implements Serializable {

    static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    @TableField(value = "created", fill = FieldFill.INSERT)
    LocalDateTime created;

    @TableField(value = "modified", fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modified;
}
