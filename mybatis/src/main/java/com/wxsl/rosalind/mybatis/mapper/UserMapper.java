package com.wxsl.rosalind.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxsl.rosalind.mybatis.entity.User;

public interface UserMapper extends BaseMapper<User> {

    default User findByUsername(String username){
       return this.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,username));
    }
}
