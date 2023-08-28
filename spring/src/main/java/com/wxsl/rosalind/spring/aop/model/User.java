package com.wxsl.rosalind.spring.aop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * 用户ID
     */
    Long id;

    /**
     * 用户名称
     */
    String name;

    /**
     * 账户名称
     */
    String account;

    /**
     * 用户密码
     */
    String password;

    /**
     * 创建时间
     */
    LocalDateTime created;

    /**
     * 修改时间
     */
    LocalDateTime modified;
}
