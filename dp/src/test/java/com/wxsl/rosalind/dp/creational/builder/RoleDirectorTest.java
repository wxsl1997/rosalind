package com.wxsl.rosalind.dp.creational.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("建造者模式")
class RoleDirectorTest {

    @Test
    @DisplayName("建造者")
    void builder() {
        RoleBuilder roleBuilder = new AngelBuilder();
        Role role = RoleDirector.build(roleBuilder);
        System.out.println(role);
    }

    @Test
    @DisplayName("建造者-隐藏")
    void build() {
        RoleBuilder roleBuilder = new AngelBuilder();
        Role role = roleBuilder.build();
        System.out.println(role);
    }
}
