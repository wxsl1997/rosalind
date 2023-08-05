package com.wxsl.rosalind.jpa.service;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.jpa.command.UserRegisterCommand;
import com.wxsl.rosalind.jpa.dto.UserDto;
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
class UserServiceTest extends BaseTest {
    @Resource
    UserService userService;

    @ParameterizedTest
    @MethodSource("registerSourceProvider")
    void register(String username, String password) {
        Assertions.assertNull(userService.findByUsername(username));

        UserRegisterCommand user = UserRegisterCommand.builder()
                .username(username)
                .password(password)
                .build();
        userService.register(user);

        UserDto row = userService.findByUsername(username);
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