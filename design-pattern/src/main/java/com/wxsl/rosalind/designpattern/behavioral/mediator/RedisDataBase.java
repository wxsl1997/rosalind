package com.wxsl.rosalind.designpattern.behavioral.mediator;

public class RedisDataBase extends BaseDataBase {

    public RedisDataBase(Mediator mediator) {
        super(mediator);
    }

    @Override
    public DataBaseType dataBaseType() {
        return DataBaseType.REDIS;
    }

    @Override
    public void display() {
        System.out.println("redis:" + stringList);
    }
}
