package com.wxsl.rosalind.jpa.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxsl1997
 */

@Data
@NoArgsConstructor
public class UserDto {

    Long id;

    String username;

    String password;

    public UserDto(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
