package com.wxsl.rosalind.mybatis.service;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.mybatis.command.UserRegisterCommand;
import com.wxsl.rosalind.mybatis.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.annotation.Resource;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author wxsl1997
 */
class UserInfoServiceTest extends BaseTest {

    @Resource
    UserInfoService userInfoService;

    @ParameterizedTest
    @MethodSource("registerSourceProvider")
    void register(String username, String password) {
        Assertions.assertNull(userInfoService.findByUsername(username));

        UserRegisterCommand user = UserRegisterCommand.builder()
                .username(username)
                .password(password)
                .build();
        userInfoService.register(user);

        UserDto row = userInfoService.findByUsername(username);
        Assertions.assertEquals(username, row.getUsername());
        Assertions.assertEquals(password, row.getPassword());
    }

    static Stream<Arguments> registerSourceProvider() {
        return IntStream.rangeClosed(1, 3).mapToObj(num -> {
            long random = ThreadLocalRandom.current().nextInt(100_000, 999_999);
            String username = "user-" + System.nanoTime();
            String password = String.valueOf(random);
            return Arguments.of(username, password);
        });
    }
}