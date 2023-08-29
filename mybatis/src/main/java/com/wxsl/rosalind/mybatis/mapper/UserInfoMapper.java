package com.wxsl.rosalind.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxsl.rosalind.mybatis.entity.UserInfo;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    default UserInfo findByUsername(String username){
       return this.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUsername,username));
    }
}
