package com.wxsl.rosalind.mybatis.configuration;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditableEntity {

    @TableField(value = "created", fill = FieldFill.INSERT)
    protected LocalDateTime created;

    @TableField(value = "modified", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime modified;
}