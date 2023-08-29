package com.wxsl.rosalind.jpa.service;

import com.wxsl.rosalind.jpa.command.UserRegisterCommand;
import com.wxsl.rosalind.jpa.configuration.JpaTransactional;
import com.wxsl.rosalind.jpa.dto.UserDto;
import com.wxsl.rosalind.jpa.model.UserInfo;
import com.wxsl.rosalind.jpa.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * @author wxsl1997
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UserService {

    UserInfoRepository userRepository;

    @JpaTransactional
    public void register(UserRegisterCommand command) {

        UserInfo user = UserInfo.builder()
                .username(command.getUsername())
                .password(command.getPassword())
                .build();

        // save user
        userRepository.save(user);
    }

    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
