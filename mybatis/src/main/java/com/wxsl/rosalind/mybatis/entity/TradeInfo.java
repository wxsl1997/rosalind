package com.wxsl.rosalind.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxsl.rosalind.mybatis.configuration.AuditableEntity;
import com.wxsl.rosalind.mybatis.enumeration.TradeStatusEnum;
import com.wxsl.rosalind.mybatis.util.IntEnumTypeHandler;
import lombok.*;

import java.math.BigDecimal;

/**
 * 交易表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "trade_info", autoResultMap = true)
public class TradeInfo extends AuditableEntity {

    static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    Long id;

    @TableField("user_id")
    Long userId;

    @TableField("payment")
    BigDecimal payment;

    @TableField(value = "status", typeHandler = IntEnumTypeHandler.class)
    TradeStatusEnum status;

    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    Long version;
}
