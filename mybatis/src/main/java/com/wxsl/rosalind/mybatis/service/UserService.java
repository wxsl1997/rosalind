package com.wxsl.rosalind.mybatis.service;

import com.wxsl.rosalind.mybatis.command.UserRegisterCommand;
import com.wxsl.rosalind.mybatis.configuration.MybatisTransactional;
import com.wxsl.rosalind.mybatis.converter.UserConverter;
import com.wxsl.rosalind.mybatis.dto.UserDto;
import com.wxsl.rosalind.mybatis.entity.User;
import com.wxsl.rosalind.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wxsl1997
 */
@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    UserConverter userConverter;


    @MybatisTransactional
    public void register(UserRegisterCommand command) {

        User user = User.builder()
                .username(command.getUsername())
                .password(command.getPassword())
                .build();

        // save user
        userMapper.insert(user);
    }

    public UserDto findByUsername(String username) {
        User user = userMapper.findByUsername(username);

        return userConverter.toUserDto(user);
    }
}
