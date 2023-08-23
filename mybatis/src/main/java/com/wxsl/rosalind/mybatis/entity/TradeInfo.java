package com.wxsl.rosalind.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxsl.rosalind.mybatis.enumeration.TradeStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 交易表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("trade_info")
public class TradeInfo implements Serializable {

    static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    Long id;

    @TableField("user_id")
    Long userId;

    @TableField("payment")
    BigDecimal payment;

    @TableField("status")
    TradeStatusEnum status;

    @TableField("version")
    @Version
    Long version;

    @TableField(value = "created", fill = FieldFill.INSERT)
    LocalDateTime created;

    @TableField(value = "modified", fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modified;
}
