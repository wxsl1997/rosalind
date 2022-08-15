package com.wxsl.rosalind.dp.creational.builder;

/**
 * 建造者实例
 */
public class DevilBuilder extends RoleBuilder {

    @Override
    public void buildType() {
        role.setRoleType(RoleType.DEVIL);
    }

    @Override
    public void buildSex() {
        role.setRoleSex(RoleSex.MALE);
    }

    @Override
    public void buildAppearance() {
        role.setAppearance("丑陋");
    }

    @Override
    public void buildCostume() {
        role.setCostume("黑袍");
    }

    @Override
    public void buildHairStyle() {
        role.setHairStyle("光头");
    }
}
