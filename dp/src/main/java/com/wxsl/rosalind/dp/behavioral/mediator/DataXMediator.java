package com.wxsl.rosalind.dp.behavioral.mediator;

import com.google.common.collect.Maps;

import java.util.Map;


public class DataXMediator implements Mediator {


    private Map<DataBaseType, BaseDataBase> dataBaseMap = Maps.newHashMap();

    @Override
    public void register(BaseDataBase dataBase) {
        dataBaseMap.put(dataBase.dataBaseType(), dataBase);
    }

    @Override
    public void sync(BaseDataBase dataBase, String data) {
        if (DataBaseType.MYSQL.equals(dataBase.dataBaseType())) {
            dataBaseMap.get(DataBaseType.ELASTICSEARCH).insert(data);
            dataBaseMap.get(DataBaseType.REDIS).insert(data);
        }
        if (DataBaseType.ELASTICSEARCH.equals(dataBase.dataBaseType())) {
            dataBaseMap.get(DataBaseType.MYSQL).insert(data);
        }
    }

    @Override
    public void display() {
        dataBaseMap.values().forEach(BaseDataBase::display);
    }
}
