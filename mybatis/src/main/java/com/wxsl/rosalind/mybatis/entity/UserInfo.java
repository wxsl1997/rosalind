package com.wxsl.rosalind.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("user_info")
public class UserInfo implements Serializable {

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

    @TableField(value = "created", fill = FieldFill.INSERT)
    LocalDateTime created;

    @TableField(value = "modified", fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modified;
}
