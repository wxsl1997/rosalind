package com.wxsl.rosalind.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxsl.rosalind.mybatis.configuration.AuditableEntity;
import lombok.*;

/**
 * 用户表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("user_info")
public class UserInfo extends AuditableEntity {

    static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    Long id;

    @TableField("username")
    String username;

    @TableField("password")
    String password;

    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    Long version;
}
