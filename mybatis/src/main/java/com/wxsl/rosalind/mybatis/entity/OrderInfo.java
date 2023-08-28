package com.wxsl.rosalind.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxsl.rosalind.mybatis.enumeration.OrderStatusEnum;
import com.wxsl.rosalind.mybatis.util.IntEnumTypeHandler;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "order_info", autoResultMap = true)
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

    @TableField(value = "status", typeHandler = IntEnumTypeHandler.class)
    OrderStatusEnum status;

    @TableField("quantity")
    Integer quantity;

    @Version
    @TableField(value = "version", fill = FieldFill.INSERT)
    Long version;

    @TableField(value = "created", fill = FieldFill.INSERT)
    LocalDateTime created;

    @TableField(value = "modified", fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modified;
}
