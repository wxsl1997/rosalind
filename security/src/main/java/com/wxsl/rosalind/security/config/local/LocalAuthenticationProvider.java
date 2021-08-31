package com.wxsl.rosalind.security.config.local;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LocalAuthenticationProvider extends DaoAuthenticationProvider {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public LocalAuthenticationProvider(UserDetailsService userDetailsService) {
        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(PASSWORD_ENCODER);
    }
}
