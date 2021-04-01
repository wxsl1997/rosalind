package com.wxsl.rosalind.security.catalog.dao;

import com.wxsl.rosalind.security.catalog.model.User;
import com.wxsl.rosalind.security.jpa.BaseJpaRepository;

public interface UserDao extends BaseJpaRepository<User, Long> {

    User findByUsername(String username);
}
