package com.wxsl.rosalind.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxsl.rosalind.mybatis.configuration.AuditableEntity;
import com.wxsl.rosalind.mybatis.enumeration.OrderStatusEnum;
import com.wxsl.rosalind.mybatis.util.IntEnumTypeHandler;
import lombok.*;

import java.math.BigDecimal;

/**
 * 订单表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "order_info", autoResultMap = true)
public class OrderInfo extends AuditableEntity {

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
}
