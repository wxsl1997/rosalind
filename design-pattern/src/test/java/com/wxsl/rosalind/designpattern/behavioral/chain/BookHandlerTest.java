package com.wxsl.rosalind.designpattern.behavioral.chain;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

@DisplayName("责任链模式")
class BookHandlerTest {

    @Test
    @DisplayName("责任链测试")
    void handleRequest() {
        //测试职责链模式
        BookHandler baseBookHandler = new BaseBookHandler();
        {
            BookHandler advanceBookHandler = new AdvanceBookHandler();
            baseBookHandler.setSuccessor(advanceBookHandler);

            BookHandler masterBookHandler = new MasterBookHandler();
            advanceBookHandler.setSuccessor(masterBookHandler);
        }
        //构建请求参数
        BookRequest bookRequest = new BookRequest();
        {
            List<Book> baseBook = ImmutableList.<Book>builder()
                    .add(new Book("编程基础", Level.BASE))
                    .add(new Book("设计模式", Level.BASE))
                    .add(new Book("数据结构", Level.BASE))
                    .build();
            List<Book> advanceBook = ImmutableList.<Book>builder()
                    .add(new Book("框架", Level.ADVANCE))
                    .add(new Book("源码", Level.ADVANCE))
                    .add(new Book("理论", Level.ADVANCE))
                    .build();
            List<Book> masterBook = ImmutableList.<Book>builder()
                    .add(new Book("中间件", Level.MASTER))
                    .add(new Book("分布式", Level.MASTER))
                    .build();
            Map<Level, List<Book>> levelListMap = new ImmutableMap.Builder<Level, List<Book>>()
                    .put(Level.ADVANCE, advanceBook)
                    .put(Level.BASE, baseBook)
                    .put(Level.MASTER, masterBook)
                    .build();
            bookRequest.setLevelListMap(levelListMap);
        }
        //处理请求
        baseBookHandler.handleRequest(bookRequest);
    }
}
