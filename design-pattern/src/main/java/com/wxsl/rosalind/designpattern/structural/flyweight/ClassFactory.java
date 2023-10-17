package com.wxsl.rosalind.designpattern.structural.flyweight;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

import static java.lang.Class.forName;

/**
 * 享元工厂类
 */
public class ClassFactory {

    /**
     * 享元池
     */
    private static Map<String, Object> map = Maps.newHashMap();

    /**
     * 根据className 获取享元池中 类对象
     */
    public static Object object(String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (Objects.isNull(map.get(className))) {
            Class clazz = forName(className);
            map.put(className, clazz.newInstance());
        }
        return map.get(className);
    }
}
