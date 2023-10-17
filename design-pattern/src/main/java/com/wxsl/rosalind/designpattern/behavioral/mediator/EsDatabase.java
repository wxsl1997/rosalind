package com.wxsl.rosalind.designpattern.behavioral.mediator;

public class EsDatabase extends BaseDataBase {

    public EsDatabase(Mediator mediator) {
        super(mediator);
    }

    @Override
    public DataBaseType dataBaseType() {
        return DataBaseType.ELASTICSEARCH;
    }

    @Override
    public void display() {
        System.out.println("es:" + stringList);
    }
}

