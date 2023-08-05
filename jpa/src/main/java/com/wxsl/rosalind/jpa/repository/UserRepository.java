package com.wxsl.rosalind.jpa.repository;

import com.wxsl.rosalind.jpa.configuration.BaseJpaRepository;
import com.wxsl.rosalind.jpa.dto.UserDto;
import com.wxsl.rosalind.jpa.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author wxsl1997
 */
public interface UserRepository extends BaseJpaRepository<User, Long> {
    @Query(value = "select new com.wxsl.rosalind.jpa.dto.UserDto(id, username, password) from User where username=:username")
    UserDto findByUsername(@Param("username") String username);
}
