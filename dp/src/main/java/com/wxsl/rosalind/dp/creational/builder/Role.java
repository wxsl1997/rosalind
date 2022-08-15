package com.wxsl.rosalind.dp.creational.builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Role {

    /**
     * 角色类型
     */
    private RoleType roleType;

    /**
     * 角色性别
     */
    private RoleSex roleSex;

    /**
     * 角色容貌
     */
    private String appearance;

    /**
     * 角色服饰
     */
    private String costume;

    /**
     * 角色发型
     */
    private String hairStyle;
}
