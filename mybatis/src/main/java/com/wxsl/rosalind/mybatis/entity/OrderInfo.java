package com.wxsl.rosalind.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxsl.rosalind.mybatis.enumeration.OrderStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("order_info")
public class OrderInfo implements Serializable {

    static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    Long id;

    @TableField("user_id")
    Long userId;

    @TableField("trade_id")
    Long tradeId;

    @TableField("product_id")
    Long productId;

    @TableField("payment")
    BigDecimal payment;

    @TableField("status")
    OrderStatusEnum status;

    @TableField("version")
    @Version
    Long version;

    @TableField("quantity")
    Integer quantity;

    @TableField(value = "created", fill = FieldFill.INSERT)
    LocalDateTime created;

    @TableField(value = "modified", fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modified;
}
