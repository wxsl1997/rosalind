package com.wxsl.rosalind.spring.ioc.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDemo {

    private String name = "dev";

    private LocalDateTime created = LocalDateTime.now();
}
