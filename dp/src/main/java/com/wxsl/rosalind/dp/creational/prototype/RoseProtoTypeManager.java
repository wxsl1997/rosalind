package com.wxsl.rosalind.dp.creational.prototype;

import lombok.Setter;
import net.sf.cglib.beans.BeanCopier;

import java.util.Map;

/**
 * 玫瑰原型管理器
 */
@Setter
public class RoseProtoTypeManager {

    private RoseProtoTypeManager() {
    }

    private static class IoDHClass {
        private final static RoseProtoTypeManager instance = new RoseProtoTypeManager();
    }

    public static RoseProtoTypeManager getInstance() {
        return IoDHClass.instance;
    }

    private Map<String, Rose> roseMap;

    /**
     * 根据key得到rose副本
     */
    public Rose getClonedRose(String roseType) {
        Rose sourceRose = roseMap.get(roseType);
        Rose targetRose = new Rose();

        BeanCopier beanCopier = BeanCopier.create(Rose.class, Rose.class, false);
        beanCopier.copy(sourceRose, targetRose, null);

        return targetRose;
    }
}
