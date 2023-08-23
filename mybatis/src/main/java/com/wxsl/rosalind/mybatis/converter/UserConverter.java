package com.wxsl.rosalind.mybatis.converter;

import com.wxsl.rosalind.mybatis.dto.UserDto;
import com.wxsl.rosalind.mybatis.entity.User;
import org.mapstruct.Mapper;

/**
 * @author wxsl1997
 */
@Mapper
public interface UserConverter {

    UserDto toUserDto(User user);
}
