package com.wxsl.rosalind.designpattern.creational.builder;

/**
 * 建造者实例
 */
public class AngelBuilder extends RoleBuilder {

    @Override
    public void buildType() {
        role.setRoleType(RoleType.ANGEL);
    }

    @Override
    public void buildSex() {
        role.setRoleSex(RoleSex.FEMALE);
    }

    @Override
    public void buildAppearance() {
        role.setAppearance("漂亮");
    }

    @Override
    public void buildCostume() {
        role.setCostume("白裙");
    }

    @Override
    public void buildHairStyle() {
        role.setHairStyle("纤柔");
    }

}
