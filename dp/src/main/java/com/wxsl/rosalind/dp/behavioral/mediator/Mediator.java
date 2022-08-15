package com.wxsl.rosalind.dp.behavioral.mediator;

/**
 * 抽象中介者
 */
public interface Mediator {

    /**
     * 注册数据源
     */
    void register(BaseDataBase dataBase);

    /**
     * 数据同步
     */
    void sync(BaseDataBase dataBase, String data);

    /**
     * 数据展示
     */
    void display();
}
