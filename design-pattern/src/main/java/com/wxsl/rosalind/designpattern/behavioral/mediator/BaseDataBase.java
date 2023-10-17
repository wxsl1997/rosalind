package com.wxsl.rosalind.designpattern.behavioral.mediator;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 抽象同事类
 */
public abstract class BaseDataBase {

    /**
     * 存储数据集
     */
    protected List<String> stringList = Lists.newArrayList();

    /**
     * 维持抽象中介者引用
     */
    private Mediator mediator;

    /**
     * 构造函数
     */
    public BaseDataBase(Mediator mediator) {
        this.mediator = mediator;
    }

    /**
     * 插入数据项
     */
    public void insert(String data) {
        stringList.add(data);
    }

    public void sync(String data) {
        //转发调用
        mediator.sync(this, data);
    }

    public abstract void display();

    /**
     * 数据库类型
     */
    public abstract DataBaseType dataBaseType();
}
