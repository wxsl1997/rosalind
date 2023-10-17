package com.wxsl.rosalind.designpattern.behavioral.chain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    private String name;

    private Level level;
}
