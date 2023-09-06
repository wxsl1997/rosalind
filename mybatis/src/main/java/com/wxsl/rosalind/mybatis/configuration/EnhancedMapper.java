package com.wxsl.rosalind.mybatis.configuration;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.collect.Lists;

import java.util.Collection;

/**
 * @author wxsl1997
 */

public interface EnhancedMapper<T> extends BaseMapper<T> {

    int DEFAULT_BATCH_SIZE = 1000;

    default void saveBatch(Collection<T> entities) {
        EnhanceMapperHelper.saveBatch(this, entities, DEFAULT_BATCH_SIZE);
    }

    default void updateBatchByIds(Collection<T> entities) {
        EnhanceMapperHelper.updateBatchByIds(this, entities, DEFAULT_BATCH_SIZE);
    }

    default void saveOrUpdate(T entity) {
        EnhanceMapperHelper.saveOrUpdateBatch(this, Lists.newArrayList(entity), DEFAULT_BATCH_SIZE);
    }

    default void saveOrUpdateBatch(Collection<T> entities) {
        EnhanceMapperHelper.saveOrUpdateBatch(this, entities, DEFAULT_BATCH_SIZE);
    }

    default LambdaQueryWrapper<T> lambdaWrapper() {
        return new LambdaQueryWrapper<>();
    }
}
