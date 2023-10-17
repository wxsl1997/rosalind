package com.wxsl.rosalind.designpattern.behavioral.mediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("中介者模式")
class MediatorTest {

    private Mediator mediator;

    private BaseDataBase mysqlDataBase;

    @BeforeEach
    void setUp() {
        mediator = new DataXMediator();
        mysqlDataBase = new MysqlDataBase(mediator);
        BaseDataBase esDataBase = new EsDatabase(mediator);
        BaseDataBase redisDataBase = new RedisDataBase(mediator);

        mediator.register(mysqlDataBase);
        mediator.register(esDataBase);
        mediator.register(redisDataBase);
    }

    @Test
    @DisplayName("中介者测试")
    void sync() {
        mysqlDataBase.insert("carola");
        mysqlDataBase.sync("carola");
        mediator.display();
    }
}
