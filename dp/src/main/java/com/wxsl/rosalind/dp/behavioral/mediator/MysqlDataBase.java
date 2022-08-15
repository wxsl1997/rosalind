package com.wxsl.rosalind.dp.behavioral.mediator;

public class MysqlDataBase extends BaseDataBase {

    public MysqlDataBase(Mediator mediator) {
        super(mediator);
    }

    @Override
    public DataBaseType dataBaseType() {
        return DataBaseType.MYSQL;
    }

    @Override
    public void display() {
        System.out.println("mysql:" + stringList);
    }
}
