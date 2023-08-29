package com.wxsl.rosalind.jpa.repository;

import com.wxsl.rosalind.jpa.configuration.BaseJpaRepository;
import com.wxsl.rosalind.jpa.dto.UserDto;
import com.wxsl.rosalind.jpa.model.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author wxsl1997
 */
public interface UserInfoRepository extends BaseJpaRepository<UserInfo, Long> {
    @Query(value = "select new com.wxsl.rosalind.jpa.dto.UserDto(id, username, password) from UserInfo where username=:username")
    UserDto findByUsername(@Param("username") String username);
}
