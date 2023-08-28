package com.wxsl.rosalind.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxsl.rosalind.mybatis.enumeration.TradeStatusEnum;
import com.wxsl.rosalind.mybatis.util.IntEnumTypeHandler;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 交易表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "trade_info", autoResultMap = true)
public class TradeInfo implements Serializable {

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

    @TableField(value = "created", fill = FieldFill.INSERT)
    LocalDateTime created;

    @TableField(value = "modified", fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modified;
}
