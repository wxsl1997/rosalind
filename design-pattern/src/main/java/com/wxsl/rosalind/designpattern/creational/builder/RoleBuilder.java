package com.wxsl.rosalind.designpattern.creational.builder;

import lombok.Getter;

/**
 * 角色建造者
 */
@Getter
public abstract class RoleBuilder {

    protected Role role = new Role();

    public abstract void buildType();

    public abstract void buildSex();

    public abstract void buildAppearance();

    public abstract void buildCostume();

    public abstract void buildHairStyle();

    /**
     * 指挥者 隐藏
     */
    public Role build() {
        buildType();
        buildSex();
        buildAppearance();
        buildCostume();
        buildHairStyle();
        return role;
    }
}
