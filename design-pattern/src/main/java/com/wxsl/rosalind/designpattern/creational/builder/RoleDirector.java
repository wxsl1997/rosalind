package com.wxsl.rosalind.designpattern.creational.builder;

/**
 * 角色指挥者
 */
public class RoleDirector {

    /**
     * 构建 复杂角色
     */
    public static Role build(RoleBuilder roleBuilder) {
        Role role = roleBuilder.getRole();
        roleBuilder.buildType();
        roleBuilder.buildSex();
        roleBuilder.buildAppearance();
        roleBuilder.buildCostume();
        roleBuilder.buildHairStyle();
        return role;
    }
}
