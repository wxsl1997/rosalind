package com.wxsl.rosalind.mybatis.configuration;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.core.GenericTypeResolver;

import java.util.Collection;

/**
 * @author wxsl1997
 */


public interface EnhancedMapper<T> extends BaseMapper<T> {

    default int insertBatch(Collection<T> entities) {
        return entities.stream().mapToInt(this::insert).sum();
    }

    default int updateBatchByIds(Collection<T> entities) {
        return entities.stream().mapToInt(this::updateById).sum();
    }

    /**
     * execute update if id is not empty or else execute insert
     *
     * @see com.baomidou.mybatisplus.extension.service.impl.ServiceImpl#saveOrUpdate(java.lang.Object)
     */
    default int insertOrUpdate(T entity) {
        Class<?> entityClass = GenericTypeResolver.resolveTypeArgument(this.getClass(), EnhancedMapper.class);
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
        return StringUtils.checkValNull(idVal) ? insert(entity) : updateById(entity);
    }

    default int insertOrUpdate(Collection<T> entities) {
        return entities.stream().mapToInt(this::insertOrUpdate).sum();
    }
}
