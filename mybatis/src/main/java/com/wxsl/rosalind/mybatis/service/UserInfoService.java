package com.wxsl.rosalind.mybatis.service;

import com.wxsl.rosalind.mybatis.command.UserRegisterCommand;
import com.wxsl.rosalind.mybatis.configuration.MybatisTransactional;
import com.wxsl.rosalind.mybatis.converter.UserInfoConverter;
import com.wxsl.rosalind.mybatis.dto.UserDto;
import com.wxsl.rosalind.mybatis.entity.UserInfo;
import com.wxsl.rosalind.mybatis.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wxsl1997
 */
@Service
public class UserInfoService {

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    UserInfoConverter userInfoConverter;


    @MybatisTransactional
    public void register(UserRegisterCommand command) {

        UserInfo user = UserInfo.builder()
                .username(command.getUsername())
                .password(command.getPassword())
                .build();

        // save user
        userInfoMapper.insert(user);
    }

    public UserDto findByUsername(String username) {
        UserInfo user = userInfoMapper.findByUsername(username);

        return userInfoConverter.toUserDto(user);
    }
}
