package com.wxsl.rosalind.designpattern.behavioral.chain;

import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * 抽象处理者
 */
@Setter
public abstract class BookHandler {

    /**
     * 维持继任者引用
     */
    protected BookHandler successor;


    public void handleRequest(BookRequest bookRequest) {
        List<Book> books = bookRequest.getLevelListMap().get(level());
        //处理部分请求
        readBook(books);

        if (Objects.nonNull(successor)) {
            //转发剩余请求
            successor.handleRequest(bookRequest);
        }
    }

    protected abstract void readBook(List<Book> bookList);

    protected abstract Level level();
}
