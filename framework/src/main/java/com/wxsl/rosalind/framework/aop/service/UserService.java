package com.wxsl.rosalind.framework.aop.service;

import com.wxsl.rosalind.framework.aop.annotation.ApiMethod;
import com.wxsl.rosalind.framework.aop.model.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;


@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {

    public User loginIn(@Nonnull User user) {
        log.info("login in :{}", user.getName());
        return user;
    }

    @ApiMethod
    public User loginOut(@Nonnull User user) {
        log.info("login out :{}", user.getName());
        return user;
    }
}
