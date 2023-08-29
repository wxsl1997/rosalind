package com.wxsl.rosalind.mybatis.converter;

import com.wxsl.rosalind.mybatis.dto.UserDto;
import com.wxsl.rosalind.mybatis.entity.UserInfo;
import org.mapstruct.Mapper;

/**
 * @author wxsl1997
 */
@Mapper
public interface UserInfoConverter {

    UserDto toUserDto(UserInfo user);
}
