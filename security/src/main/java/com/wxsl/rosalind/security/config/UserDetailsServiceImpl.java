package com.wxsl.rosalind.security.config;

import com.wxsl.rosalind.security.catalog.dao.UserDao;
import com.wxsl.rosalind.security.catalog.model.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Primary
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserDetailsService {

    UserDao userDao;

    @Override
    public RosalindUser loadUserByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(String.format("not exist user:%s", username));
        }
        return RosalindUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
