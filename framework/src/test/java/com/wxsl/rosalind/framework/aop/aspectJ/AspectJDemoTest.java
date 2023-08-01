package com.wxsl.rosalind.framework.aop.aspectJ;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.framework.aop.model.User;
import com.wxsl.rosalind.framework.aop.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AspectJDemo")
class AspectJDemoTest extends BaseTest {

    @Test
    @DisplayName("aspectj")
    void before() {

        //获取 目标对象
        UserService userService = applicationContext.getBean(UserService.class);

        //获取 参数
        User hermia = applicationContext.getBean("hermia", User.class);

        //调用 方法
        userService.loginIn(hermia);
    }
}
