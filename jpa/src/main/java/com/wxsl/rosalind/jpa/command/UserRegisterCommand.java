package com.wxsl.rosalind.jpa.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxsl1997
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterCommand {

    String username;

    String password;
}
