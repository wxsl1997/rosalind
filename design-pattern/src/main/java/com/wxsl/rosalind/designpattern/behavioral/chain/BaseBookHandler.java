package com.wxsl.rosalind.designpattern.behavioral.chain;

import java.util.List;

/**
 * 具体处理者
 */
public class BaseBookHandler extends BookHandler {

    @Override
    public void readBook(List<Book> bookList) {
        bookList.forEach(System.out::println);
    }

    @Override
    public Level level() {
        return Level.BASE;
    }
}
