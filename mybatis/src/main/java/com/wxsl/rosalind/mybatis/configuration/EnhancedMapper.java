package com.wxsl.rosalind.mybatis.configuration;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author wxsl1997
 */

public interface EnhancedMapper<T> extends BaseMapper<T> {

    int DEFAULT_BATCH_SIZE = 1000;

    @MybatisTransactional
    default void saveBatch(Collection<T> entities) {
        EnhanceMapperHelper.saveBatch(this, entities, DEFAULT_BATCH_SIZE);
    }

    default void updateBatchByIds(Collection<T> entities) {
        EnhanceMapperHelper.updateBatchByIds(this, entities, DEFAULT_BATCH_SIZE);
    }

    default int saveOrUpdate(T entity) {
        return EnhanceMapperHelper.saveOrUpdate(this, entity);
    }

    default int saveOrUpdateBatch(Collection<T> entities) {
        return entities.stream().mapToInt(this::saveOrUpdate).sum();
    }
}
