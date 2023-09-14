package com.wxsl.rosalind.mybatis.configuration;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.google.common.collect.Lists;
import com.wxsl.rosalind.mybatis.util.StreamUtils;
import lombok.experimental.UtilityClass;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.core.GenericTypeResolver;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * @author wxsl1997
 */
@UtilityClass
public final class EnhanceMapperHelper {

    /**
     * @see ServiceImpl#saveBatch(Collection)
     */
    public static <T> boolean saveBatch(EnhancedMapper<T> proxyMapper, Collection<T> entities, int batchSize) {
        Class<?> mapper = deduceMapper(proxyMapper);
        String sqlStatement = SqlHelper.getSqlStatement(mapper, SqlMethod.INSERT_ONE);
        return executeBatch(mapper, entities, batchSize, (sqlSession, entity) -> sqlSession.insert(sqlStatement, entity));
    }

    /**
     * @see ServiceImpl#saveOrUpdate(Object)
     */
    public <T> boolean saveOrUpdateBatch(EnhancedMapper<T> proxyMapper, Collection<T> entities, int batchSize) {
        if (CollectionUtils.isEmpty(entities)) {
            return false;
        }

        Class<?> entityClass = entityClass(proxyMapper);
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");


        List<Serializable> ids = entities.stream().map(entity -> (Serializable) ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty())).filter(StringUtils::checkValNotNull).collect(Collectors.toList());

        List<T> existEntities = CollectionUtils.isEmpty(ids)
                ? Lists.newArrayList()
                : proxyMapper.selectBatchIds(ids);
        Set<Serializable> existIds = StreamUtils.mapAsSet(existEntities, entity -> (Serializable) ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty()));


        Class<?> mapper = deduceMapper(proxyMapper);

        return executeBatch(mapper, entities, batchSize, (sqlSession, entity) -> {
            Serializable id = (Serializable) ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
            // execute insert if idVal is null or selectById is null
            if (StringUtils.checkValNull(id) || !existIds.contains(id)) {
                proxyMapper.insert(entity);
            }
            // or else execute update by id
            else {
                proxyMapper.updateById(entity);
            }
        });
    }

    public static <T> boolean updateBatchByIds(EnhancedMapper<T> proxyMapper, Collection<T> entities, int batchSize) {
        Class<?> mapper = deduceMapper(proxyMapper);
        String sqlStatement = SqlHelper.getSqlStatement(mapper, SqlMethod.UPDATE_BY_ID);
        return executeBatch(mapper, entities, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(sqlStatement, param);
        });
    }

    private static <T> boolean executeBatch(Class<?> mapper, Collection<T> entities, int batchSize, BiConsumer<SqlSession, T> consumer) {
        Class<?> entityClass = GenericTypeResolver.resolveTypeArgument(mapper, EnhancedMapper.class);
        Log log = LogFactory.getLog(mapper);
        return SqlHelper.executeBatch(entityClass, log, entities, batchSize, consumer);
    }

    private static <T> Class<?> deduceMapper(EnhancedMapper<T> proxyMapper) {
        //noinspection OptionalGetWithoutIsPresent
        return Arrays.stream(proxyMapper.getClass().getInterfaces()).filter(EnhancedMapper.class::isAssignableFrom).findFirst().get();
    }

    private static <T> Class<T> entityClass(EnhancedMapper<T> proxyMapper) {
        Class<?> mapper = deduceMapper(proxyMapper);
        //noinspection unchecked
        return (Class<T>) GenericTypeResolver.resolveTypeArgument(mapper, EnhancedMapper.class);
    }
}
